// ===== Bridge Pattern Template =====

// Implementor: interface for the "implementation" hierarchy
interface Implementor {
    void implementedOperation();
}

// ConcreteImplementorA / B: independent implementation variants
class ConcreteImplementorA implements Implementor {
    @Override
    public void implementedOperation() {
        System.out.println("ImplementorA doing the work");
    }
}

class ConcreteImplementorB implements Implementor {
    @Override
    public void implementedOperation() {
        System.out.println("ImplementorB doing the work");
    }
}

// Abstraction: HAS-A Implementor (set via constructor = Dependency Inversion)
abstract class Abstraction {
    protected final Implementor implementor;

    protected Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void operation();
}

// RefinedAbstraction: extends the abstraction hierarchy independently
class RefinedAbstraction extends Abstraction {
    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operation() {
        System.out.print("RefinedAbstraction -> ");
        implementor.implementedOperation(); // delegate the "how" to Implementor
    }
}

public class BridgeTemplate {
    public static void main(String[] args) {
        Abstraction abs1 = new RefinedAbstraction(new ConcreteImplementorA());
        Abstraction abs2 = new RefinedAbstraction(new ConcreteImplementorB());

        abs1.operation();
        abs2.operation();
        // Abstraction hierarchy and Implementor hierarchy vary independently
    }
}
