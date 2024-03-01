package codingdojo;

public class Inventory {
    private Equipment equipment;

    public Inventory(Equipment equipment) {
        this.equipment = equipment;
    }

    Equipment getEquipment() {
        return equipment;
    }

    public float getSumDamageModifiers(){
        return equipment.getSumDamageModifier() + additionalDamageModifier();
    }


    public float additionalDamageModifier(){
        //this should return the sum of any additional
        //modifiers in the inventory which are NOT on
        // the equipment;

        return 0f;
    }
}
