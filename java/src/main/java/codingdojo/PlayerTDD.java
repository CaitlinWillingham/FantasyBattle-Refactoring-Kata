package codingdojo;

public class PlayerTDD {

    Inventory inventory;
    Stats stats;

    public PlayerTDD(Inventory inventory, Stats stats) {
        this.inventory = inventory;
        this.stats = stats;
    }

    public Damage calculateDamage() {
        int baseDamage = getBaseDamage();
        float damageModifier = getDamageModifier();
        float totalDamage = baseDamage * damageModifier;
        return new Damage((int) totalDamage);
    }

    private int getBaseDamage() {
        Equipment equipment = this.inventory.getEquipment();
        Item leftHand = equipment.getLeftHand();
        Item rightHand = equipment.getRightHand();
        Item head = equipment.getHead();
        Item feet = equipment.getFeet();
        Item chest = equipment.getChest();
        return leftHand.getBaseDamage() +
                rightHand.getBaseDamage() +
                head.getBaseDamage() +
                feet.getBaseDamage() +
                chest.getBaseDamage();
    }

    private float getDamageModifier() {
        Equipment equipment = this.inventory.getEquipment();
        Item leftHand = equipment.getLeftHand();
        Item rightHand = equipment.getRightHand();
        Item head = equipment.getHead();
        Item feet = equipment.getFeet();
        Item chest = equipment.getChest();
        float strengthModifier = stats.getStrength() * 0.1f;
        return strengthModifier +
                leftHand.getDamageModifier() +
                rightHand.getDamageModifier() +
                head.getDamageModifier() +
                feet.getDamageModifier() +
                chest.getDamageModifier();
    }
}
