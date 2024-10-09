class BowlingGame {
    enum class FrameType {
        Standard,
        Final
    }

    data class Frame(val rolls: List<Int> = emptyList(), val frameType: FrameType = FrameType.Standard) {
        val isStrike: Boolean
            get() = rolls.isNotEmpty() && rolls[0] == MAX_SCORE
        val isSpare: Boolean
            get() = rolls.size >= 2 && rolls[0] + rolls[1] == MAX_SCORE
        val isStandard: Boolean
            get() = frameType == FrameType.Standard
        val isComplete: Boolean
            get() {
                return when (frameType) {
                    FrameType.Final -> if (isStrike || isSpare) rolls.size == 3 else rolls.size == 2
                    else -> isStrike || rolls.size == 2
                }
            }
        val isValid: Boolean
            get() = when (frameType) {
                FrameType.Standard -> rolls.sum() <= MAX_SCORE
                FrameType.Final ->
                    if (isStrike) {
                        if (rolls.size == 3 && rolls[1] != MAX_SCORE) {
                            rolls[1] + rolls[2] <= MAX_SCORE
                        } else true
                    } else true
            }
    }

    data class FrameTracker(private val frames: List<Frame> = emptyList(), private val currentFrame: Frame = Frame()) {
        fun addRoll(roll: Int): FrameTracker {
            check(roll in 0..MAX_SCORE)
            val newCurrentFrame = if (currentFrame.isComplete) Frame(
                rolls = listOf(roll),
                frameType = if (frames.size < LAST_STANDARD_FRAME) FrameType.Standard else FrameType.Final
            ) else currentFrame.copy(rolls = currentFrame.rolls + roll)
            check(newCurrentFrame.isValid)
            return copy(
                currentFrame = newCurrentFrame,
                frames = if (newCurrentFrame.isComplete) frames + newCurrentFrame else frames
            )
        }

        private val isStandardFrame: Boolean
            get() = currentFrame.isStandard

        val isStrike: Boolean
            get() = isStandardFrame && currentFrame.isStrike

        val isSpare: Boolean
            get() = isStandardFrame && currentFrame.isSpare

        val areFramesComplete: Boolean
            get() = frames.size == 10 && frames.all { it.isComplete }

    }

    data class ScoreAccumulator(
        val score: Int = 0, val frameTracker: FrameTracker = FrameTracker(),
        private val rollIndex: Int = 0, private val rollIndexMultipliers: Array<Int> = Array(MAX_ROLL_INDEX) { 0 }
    ) {
        fun addRoll(roll: Int): ScoreAccumulator {
            val newFrameTracker = frameTracker.addRoll(roll)
            val newScore = score + roll + (roll * getBonusMultiplier(rollIndex))
            when {
                newFrameTracker.isStrike -> {
                    addBonusForStrike(rollIndex)
                }

                newFrameTracker.isSpare -> {
                    addBonusForSpare(rollIndex)
                }
            }
            return copy(score = newScore, frameTracker = newFrameTracker, rollIndex = rollIndex + 1)
        }

        val areAllFramesComplete: Boolean
            get() = frameTracker.areFramesComplete

        private fun addBonusForSpare(rollIndex: Int) {
            rollIndexMultipliers[rollIndex + 1]++
        }

        private fun addBonusForStrike(rollIndex: Int) {
            rollIndexMultipliers[rollIndex + 1]++
            rollIndexMultipliers[rollIndex + 2]++
        }

        private fun getBonusMultiplier(rollIndex: Int) =
            if (rollIndex < MAX_ROLL_INDEX) rollIndexMultipliers[rollIndex] else 0

    }

    private var accumulator: ScoreAccumulator = ScoreAccumulator()
    fun roll(pins: Int) {
        check(!accumulator.areAllFramesComplete)
        accumulator = accumulator.addRoll(pins)
    }

    fun score(): Int {
        check(accumulator.areAllFramesComplete)
        return accumulator.score
    }

    companion object {
        private const val MAX_SCORE = 10
        private const val MAX_ROLL_INDEX = 20
        private const val LAST_STANDARD_FRAME = 9
    }
}