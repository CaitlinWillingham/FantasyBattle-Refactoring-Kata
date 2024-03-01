package codingdojo;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    // choose this one if you are not familiar with mocks
//    Where	What	Base Damage	Damage Modifier
//    right hand	flashy sword of danger	10	1.0
//    right hand	excalibur	20	1.5
//    left hand	round shield	0	1.4
//    feet	ten league boots	0	0.1
//    head	helmet of swiftness	0	1.2
//    chest	breastplate of steel	0	1.4
    @Test
    void damageCalculationsForBaseValues() {
        Inventory baseInventory = generateBaseInventory();
        Stats stats = generateBaseStats();
        SimpleEnemy target = generateBaseSimpleEnemy();
        Damage damage = new Player(baseInventory, stats).calculateDamage(target);
        assertEquals(42, damage.getAmount());
    }

    @Test
    void damageCalculationsForEnemyWithNoBuffs() {
        Inventory baseInventory = generateBaseInventory();
        Stats stats = generateBaseStats();
        SimpleEnemy target = generateBaseSimpleEnemy();

        target.getBuffs().clear();

        Damage damage = new Player(baseInventory, stats).calculateDamage(target);
        assertEquals(47, damage.getAmount());
    }

    @Test
    void damageCalculationsForPlayerWithEmptyInventory() {
        Inventory baseInventory = generateEmptyInventory();
        Stats stats = generateBaseStats();
        SimpleEnemy target = generateBaseSimpleEnemy();

        target.getBuffs().clear();

        Damage damage = new Player(baseInventory, stats).calculateDamage(target);
        assertEquals(0, damage.getAmount());
    }

    private Stats generateBaseStats() {
        return new Stats(1);
    }

    private Inventory generateBaseInventory() {
        Item leftHand = new BasicItem("round shield", 0, 1.4f);
        Item rightHand = new BasicItem("flashy sword of danger", 10, 1.0f);
        Item head = new BasicItem("helmet of swiftness", 0, 1.2f);
        Item feet = new BasicItem("ten league boots", 0, 0.1f);
        Item chest = new BasicItem("breastplate of steel", 0, 1.4f);

        Equipment equipment = new Equipment(leftHand, rightHand, head, feet, chest);

        return new Inventory(equipment);
    }

    private Inventory generateEmptyInventory() {
        Item leftHand = new BasicItem("empty", 1, 0);
        Item rightHand = new BasicItem("empty", 1, 0);
        Item head = new BasicItem("empty", 0, 0);
        Item feet = new BasicItem("empty", 0, 0);
        Item chest = new BasicItem("empty", 0, 0);

        Equipment equipment = new Equipment(leftHand, rightHand, head, feet, chest);

        return new Inventory(equipment);
    }

    private SimpleEnemy generateBaseSimpleEnemy() {
        SimpleArmor armor = new SimpleArmor(5);
        BasicBuff buff = new BasicBuff(1, 1);
        List<Buff> buffs = new ArrayList<>();
        buffs.add(buff);
        return new SimpleEnemy(armor, buffs);
    }

}
