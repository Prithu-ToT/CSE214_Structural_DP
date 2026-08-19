// ===== Decorator Pattern Template =====

// Component: common interface for wrappers and wrappee
interface Component {
    String operation();
}

// ConcreteComponent: the base object being decorated
class ConcreteComponent implements Component {
    @Override
    public String operation() {
        return "ConcreteComponent";
    }
}

// Base Decorator: implements Component, holds a Component reference (HAS-A)
abstract class Decorator implements Component {
    protected final Component wrappee;

    protected Decorator(Component wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public String operation() {
        return wrappee.operation(); // delegate by default
    }
}

// ConcreteDecoratorA: adds behavior before/after delegating
class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(Component wrappee) {
        super(wrappee);
    }

    @Override
    public String operation() {
        return "DecoratorA(" + super.operation() + ")";
    }
}

// ConcreteDecoratorB: another independent responsibility
class ConcreteDecoratorB extends Decorator {
    public ConcreteDecoratorB(Component wrappee) {
        super(wrappee);
    }

    @Override
    public String operation() {
        return "DecoratorB(" + super.operation() + ")";
    }
}

public class DecoratorTemplate {
    public static void main(String[] args) {
        Component base = new ConcreteComponent();
        Component decorated = new ConcreteDecoratorB(new ConcreteDecoratorA(base));
        System.out.println(decorated.operation());
        // Output: DecoratorB(DecoratorA(ConcreteComponent))
    }
}
