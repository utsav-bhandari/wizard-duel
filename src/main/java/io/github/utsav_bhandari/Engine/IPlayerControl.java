package io.github.utsav_bhandari.Engine;

/**
 * Keyboard inputs. Might not be needed
 */
public interface IPlayerControl {
    /**
     * Toggle whether they want to use charge for current spell
     */
    void toggleChargeUse();

    /**
     * Move the choice
     * @param direction 1 for right, -1 for left
     */
    void moveChoice(int direction);

    /**
     * Confirm the current choice
     */
    void confirmChoice();

    /**
     * Toggle the help screen
     */
    void toggleHelp();
}
