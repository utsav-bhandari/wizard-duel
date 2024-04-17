package io.github.utsav_bhandari.Engine;

/**
 * Keyboard inputs
 */
public interface IPlayerControl {
    /**
     * Toggle whether they want to use charge for current spell
     */
    void toggleChargeUse();

    /**
     * Move the choice
     * @param direction 0 for right, 180 for left (this kinda makes sense in my head)
     */
    void moveChoice(int direction);

    /**
     * Confirm the current choice
     */
    void confirmChoice();

    void toggleHelp();
}
