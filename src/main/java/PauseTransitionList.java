import javafx.animation.PauseTransition;

//Wasted several hours on this only for it to not work anyways :)

public class PauseTransitionList {
    public PauseTransitionNode head;
    //Populate list of transitions

    PauseTransitionList(PauseTransitionNode givenHead) {
        head = givenHead;
    }

    public class PauseTransitionNode {
        PauseTransition currentP;
        PauseTransitionNode next;

        PauseTransitionNode(PauseTransition givenP) {
            currentP = givenP;
            next = null;
        }

        void add(PauseTransition newP) {
            if (currentP == null) {
                currentP = newP;
                //DEBUGGING:
                System.out.println("List was initialized!");
                return;
            }
            PauseTransitionNode TraversalNode = this;
            while (TraversalNode.next != null) {
                TraversalNode = next;
            }
            System.out.println("Past the while loop...");
            //now Traversalnode is pointing to the latest node
            PauseTransitionNode newNode = new PauseTransitionNode(newP);
            TraversalNode.next = newNode;
            //DEBUGGING:
            System.out.println("Node added to end of list!");
        }
    }
}
