// ===== Composite Pattern Template =====

import java.util.ArrayList;
import java.util.List;

// Component: common interface for Leaf and Composite
interface Component {
    void operation();

    // Child-management ops: default no-op / throw so Leaf isn't forced to implement
    default void add(Component c) {
        throw new UnsupportedOperationException("Cannot add to a leaf");
    }

    default void remove(Component c) {
        throw new UnsupportedOperationException("Cannot remove from a leaf");
    }
}

// Leaf: no children, does real work directly
class Leaf implements Component {
    private final String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("Leaf: " + name);
    }
}

// Composite: holds children, delegates/aggregates operation across them
class Composite implements Component {
    private final String name;
    private final List<Component> children = new ArrayList<>();

    public Composite(String name) {
        this.name = name;
    }

    @Override
    public void add(Component c) {
        children.add(c);
    }

    @Override
    public void remove(Component c) {
        children.remove(c);
    }

    @Override
    public void operation() {
        System.out.println("Composite: " + name);
        for (Component child : children) {
            child.operation(); // polymorphic call — no instanceof checks
        }
    }
}

public class CompositeTemplate {
    public static void main(String[] args) {
        Composite root = new Composite("root");
        Composite branch = new Composite("branch");

        branch.add(new Leaf("leaf1"));
        branch.add(new Leaf("leaf2"));

        root.add(branch);
        root.add(new Leaf("leaf3"));

        root.operation(); // client treats root, branch, leaves uniformly
    }
}
