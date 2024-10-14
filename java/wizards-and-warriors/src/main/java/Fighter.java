class Fighter {

    boolean isVulnerable() {
        return true;
    }

    int getDamagePoints(Fighter fighter) {
        return 1;
    }
}

class Warrior extends Fighter {
    @Override
    public String toString() {
        return "Fighter is a Warrior";
    }

    @Override
    boolean isVulnerable() {
        return false;
    }

    @Override
    int getDamagePoints(Fighter fighter) {
        return fighter.isVulnerable() ? 10 : 6;
    }
}

class Wizard extends Fighter {
    boolean hasPreparedSpell = false;
    @Override
    public String toString() {
        return "Fighter is a Wizard";
    }

    public void prepareSpell() {
        hasPreparedSpell = true;
    }

    @Override
    boolean isVulnerable() {
        return !hasPreparedSpell;
    }

    @Override
    int getDamagePoints(Fighter fighter) {
        return hasPreparedSpell ? 12 : 3;
    }
}
