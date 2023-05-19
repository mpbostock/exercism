class Reactor<T> {
  interface Cell<T> {
    var value: T
  }

  open inner class NotifyingCell(initialValueProvider: () -> T) : Cell<T> {
    private val callbacks = mutableListOf<(T) -> Unit>()
    fun addCallback(callback: (T) -> Unit): Subscription {
      val subscription = CallbackSubscription(callback, canceller = { callbacks -= it })
      callbacks += subscription
      return subscription
    }

    override var value: T = initialValueProvider()
      set(value) {
        val oldValue = field
        field = value
        if (oldValue != value) callbacks.forEach { it(field) }
      }
  }

  inner class InputCell(x: T) : NotifyingCell({ x })
  inner class ComputeCell(vararg inputs: NotifyingCell, private val computeFunction: (List<T>) -> T) :
      NotifyingCell({ computeFunction(inputs.map { it.value }) }) {
    init {
      inputs.last().addCallback { value = computeFunction(inputs.map { it.value }) }
    }
  }

  // Your compute cell's addCallback method must return an object
  // that implements the Subscription interface.
  interface Subscription {
    fun cancel()
  }

  inner class CallbackSubscription(private val callback: (T) -> Unit, private val canceller: ((T) -> Unit) -> Unit) :
      Subscription, (T) -> Unit by callback {
    override fun cancel() {
      canceller(this)
    }
  }

}
