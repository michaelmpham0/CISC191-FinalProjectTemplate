import org.junit.Test;

public class ConcurrencyTest {

    // Mock RPG character class
    class Player {
        int health;
        public Player() {
            this.health = 100;
        }

        public synchronized void takeDamage(int damage) {
            health -= damage;
        }

        public synchronized void heal(int amount) {
            health += amount;
        }

        public synchronized int getHealth() {
            return health;
        }
    }

    @Test
    public void testPlayerHealingFixed() throws InterruptedException {
        Player player = new Player();

        Thread damageThread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                player.takeDamage(50);
            }
        });

        Thread healThread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                player.heal(30);
            }
        });

        damageThread.start();
        healThread.start();

        damageThread.join();
        healThread.join();

        // damage is more than heal, so health will be negative
        assert(player.getHealth() == -1999900);
    }
}
