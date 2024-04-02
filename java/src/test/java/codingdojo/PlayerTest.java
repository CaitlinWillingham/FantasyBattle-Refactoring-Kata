package codingdojo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerTest {

    // choose this one if you are familiar with mocks
    @Test
    public void calculateDamageTest(){
        Player p = buildMockPlayer();
        SimpleEnemy se= buildMockEnemy();

        Damage actualDamage = p.calculateDamage(se);
        Damage expectedDamage = new Damage(0);

        assertEquals(expectedDamage.getAmount(), actualDamage.getAmount());

    }

    @Test
    public void returnsSumOfItemDamage() {
        Item item1 = new BasicItem("", 1, 1f);
        Item item2 = new BasicItem("", 2, 0f);
        Item item3 = new BasicItem("", 3, 0f);
        Item item4 = new BasicItem("", 4, 0f);
        Item item5 = new BasicItem("", 5, 0f);
        Equipment equipment = new Equipment(item1, item2, item3, item4,item5);
        Inventory inventory = new Inventory(equipment);
        PlayerTDD playerTDD = new PlayerTDD(inventory, new Stats(0));
        Damage actualDamage = playerTDD.calculateDamage();
        assertEquals(15, actualDamage.getAmount());
    }

    @Test
    public void returnsSumOfItemModifiers(){
        List<Integer> damageList = baseDamagesTotaling(1);
        List<Float> modifierList = Arrays.asList(1f, 10f, 100f, 1000f, 10000f);;
        Inventory inventory = buildInventory(damageList, modifierList);
        PlayerTDD playerTDD = new PlayerTDD(inventory, new Stats(0));
        Damage actualDamage = playerTDD.calculateDamage();
        assertEquals(11111, actualDamage.getAmount());
    }

    @Test
    public void returnsOneTenthOfStrength(){
        List<Integer> damageList = baseDamagesTotaling(1);
        List<Float> modifierList = damageModifiersTotaling(0);
        Inventory inventory = buildInventory(damageList, modifierList);
        Stats stats = new Stats(130);
        PlayerTDD playerTDD = new PlayerTDD(inventory, stats);
        Damage actualDamage = playerTDD.calculateDamage();

        assertEquals(13, actualDamage.getAmount());
    }
    @Test
    public void returnsProductOfBaseDamageAndDamageModifiers(){
        List<Integer> damageList = baseDamagesTotaling(5);
        List<Float> modifierList = damageModifiersTotaling(5);
        Inventory inventory = buildInventory(damageList, modifierList);
        Stats stats = new Stats(10);
        PlayerTDD playerTDD = new PlayerTDD(inventory, stats);
        Damage actualDamage = playerTDD.calculateDamage();

        assertEquals(30, actualDamage.getAmount());
    }


    private Player buildMockPlayer(){
        Item item =new BasicItem("item",0,0f);
        Equipment equipment = new Equipment(item,item,item,item,item);
        Inventory inventory = new Inventory(equipment);
        Stats stats = new Stats(0);
        return new Player(inventory,stats);
    }
    private SimpleEnemy buildMockEnemy(){
        SimpleArmor simpleArmor = new SimpleArmor(0);
        List<Buff> buffs = new ArrayList<>();
        return new SimpleEnemy(simpleArmor, buffs);
    }

    private Inventory buildInventory(List<Integer> baseDamages, List<Float> damageModifiers) {
        Item item1 = new BasicItem("", baseDamages.get(0), damageModifiers.get(0));
        Item item2 = new BasicItem("", baseDamages.get(1), damageModifiers.get(1));
        Item item3 = new BasicItem("", baseDamages.get(2), damageModifiers.get(2));
        Item item4 = new BasicItem("", baseDamages.get(3), damageModifiers.get(3));
        Item item5 = new BasicItem("", baseDamages.get(4), damageModifiers.get(4));
        Equipment equipment = new Equipment(item1, item2, item3, item4,item5);
        return new Inventory(equipment);
    }

    private List<Integer> baseDamagesTotaling(int total) {
        return Arrays.asList(total, 0, 0, 0, 0);
    }
    private List<Float> damageModifiersTotaling(float total) {
        return Arrays.asList(total, 0f, 0f, 0f, 0f);
    }

}
