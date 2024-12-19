// ParentException.java
class ParentException extends Exception {
    public ParentException(String message) {
        super(message);
    }
}

// ChildException.java
class ChildException extends ParentException {
    public ChildException(String message) {
        super(message);
    }
}

// ExceptionThrower.java
public class ExceptionThrower {
    // Method to throw ParentException
    public static void throwParent() throws ParentException {
        throw new ParentException("This is a ParentException");
    }

    // Method to throw ChildException
    public static void throwChild() throws ChildException {
        throw new ChildException("This is a ChildException");
    }

    public static void main(String[] args) {
        System.out.println("Calling throwParent():");
        try {
            throwParent();
        } catch (ChildException e) {
            System.out.println("Caught ChildException: " + e.getMessage());
        } catch (ParentException e) {
            System.out.println("Caught ParentException: " + e.getMessage());
        }

        System.out.println("\nCalling throwChild():");
        try {
            throwChild();
        } catch (ChildException e) {
            System.out.println("Caught ChildException: " + e.getMessage());
        } catch (ParentException e) {
            System.out.println("Caught ParentException: " + e.getMessage());
        }

        System.out.println("\nCalling throwParent() with two catches, child first:");
        try {
            throwParent();
        } catch (ChildException e) {
            System.out.println("Caught ChildException: " + e.getMessage());
        } catch (ParentException e) {
            System.out.println("Caught ParentException: " + e.getMessage());
        }

        System.out.println("\nCalling throwChild() with two catches, child first:");
        try {
            throwChild();
        } catch (ChildException e) {
            System.out.println("Caught ChildException: " + e.getMessage());
        } catch (ParentException e) {
            System.out.println("Caught ParentException: " + e.getMessage());
        }

        System.out.println("\nCalling throwParent() with two catches, parent first:");
        try {
            throwParent();
        } catch (ParentException e) {
            System.out.println("Caught ParentException: " + e.getMessage());
        }
        // Uncommenting the next block will result in a compilation error
        /*
        catch (ChildException e) {
            System.out.println("Caught ChildException: " + e.getMessage());
        }
        */

        System.out.println("\nCalling throwChild() with two catches, parent first:");
        try {
            throwChild();
        } catch (ParentException e) {
            System.out.println("Caught ParentException: " + e.getMessage());
        }
        // Uncommenting the next block will result in a compilation error
        /*
        catch (ChildException e) {
            System.out.println("Caught ChildException: " + e.getMessage());
        }
        */
    }
}
