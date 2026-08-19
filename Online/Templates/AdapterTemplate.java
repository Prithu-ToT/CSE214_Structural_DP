// ===== Adapter Pattern Template (Object Adapter — idiomatic in Java) =====

// Target: interface the client expects to work with
interface Target {
    void request();
}

// Adaptee: existing/legacy/3rd-party class with an incompatible interface
// (cannot or should not be modified)
class Adaptee {
    void specificRequest() {
        System.out.println("Adaptee's specific request executed");
    }
}

// Adapter: implements Target, HAS-A Adaptee, translates calls
class Adapter implements Target {
    private final Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        // pure translation — no new behavior added, just interface conversion
        adaptee.specificRequest();
    }
}

public class AdapterTemplate {
    public static void main(String[] args) {
        Target target = new Adapter(new Adaptee());
        target.request(); // client only ever talks to Target
    }
}

/*
 * Class Adapter alternative (inheritance-based):
 * Only works cleanly in Java if Adaptee is an interface, since Java has
 * no multiple class inheritance.
 *
 * interface Adaptee { void specificRequest(); }
 *
 * class ClassAdapter implements Target, Adaptee {
 *     public void request() { specificRequest(); }
 *     public void specificRequest() { System.out.println("..."); }
 * }
 */
