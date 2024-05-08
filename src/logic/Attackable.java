package logic;

public interface Attackable {
    void getAttacked();
    void Attack();
    int getHP();
    void setHP(int hp);
    int getDamage();
}
