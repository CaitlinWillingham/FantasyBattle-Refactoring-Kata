package codingdojo;


class Player extends Target {
    private Inventory inventory;
    private Stats stats;

    Player(Inventory inventory, Stats stats) {
        this.inventory = inventory;
        this.stats = stats;
    }

    Damage calculateDamage(Target other) {
        int baseDamage = getBaseDamage();
        float damageModifier = getDamageModifier();
        int totalDamage = Math.round(baseDamage * damageModifier);
        int soak = getSoak(other, totalDamage);
        return new Damage(Math.max(0, totalDamage - soak));
    }

    private int getSoak(Target other, int totalDamage) {
        int soak = 0;
        if (other instanceof Player) {
            // TODO: Not implemented yet
            //  Add friendly fire
            soak = totalDamage;
        } else if (other instanceof SimpleEnemy) {
            SimpleEnemy simpleEnemy = (SimpleEnemy) other;
            soak = simpleEnemy.getSoak();

        }
        return soak;
    }

    private float getDamageModifier() {
        float strengthModifier = stats.getStrength() * 0.1f;
        return strengthModifier + inventory.getSumDamageModifiers();
    }

    private int getBaseDamage() {
        Equipment equipment = this.inventory.getEquipment();
        return equipment.getSumBaseDamage();
    }
}
