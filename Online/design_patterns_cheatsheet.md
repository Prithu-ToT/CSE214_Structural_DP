# Structural Patterns Cheat Sheet — Decorator, Composite, Bridge, Adapter

## Quick Comparison

| Pattern   | Intent | Relationship | # of Hierarchies | Changes interface? |
|-----------|--------|---------------|-------------------|---------------------|
| Decorator | Add responsibilities dynamically, without subclassing | Wraps same-type object (has-a, recursive) | 1 (Component) | No — same interface in/out |
| Composite | Treat individual objects & groups uniformly (part-whole tree) | Container holds children of same Component type | 1 (Component) | No — uniform interface |
| Bridge    | Decouple abstraction from implementation so both vary independently | Abstraction has-a Implementor (set up once, structurally) | 2 (Abstraction + Implementor) | No — separates *what* from *how* |
| Adapter   | Convert an incompatible interface into one the client expects | Adapter wraps Adaptee, implements Target | 2 (Target + Adaptee, unrelated) | Yes — translates one interface to another |

---

## Decorator
**Problem:** Need to add/remove responsibilities to individual objects at runtime without exploding subclass combinations.

**Structure**
```
Component (interface)
 ├── ConcreteComponent
 └── Decorator (implements Component, HAS-A Component)
      ├── ConcreteDecoratorA
      └── ConcreteDecoratorB
```
- Decorator implements the **same interface** it wraps → decorators are stackable/recursive.
- Each decorator calls `super.operation()` (or wrappee's method) then adds behavior.

**SOLID:** Open/Closed (extend via new decorators, don't modify Component); Liskov (decorator is substitutable for Component).

**Exam trigger words:** "add behavior dynamically", "avoid subclass explosion", "wrap at runtime", "toppings/coffee/pizza example", "combine features in any order".

---

## Composite
**Problem:** Need to represent part-whole tree hierarchies and let clients treat single objects (Leaf) and groups (Composite) the same way.

**Structure**
```
Component (interface: common ops, e.g. add/remove/operation)
 ├── Leaf (no children, implements operation directly)
 └── Composite (HAS-A List<Component>, delegates operation to children)
```
- Client only ever talks to `Component` — never checks "is this a leaf or composite".
- `add`/`remove` conceptually belong on Composite; if declared on Component, Leaf should throw `UnsupportedOperationException` (or use default methods in Java interfaces).

**SOLID:** Open/Closed (new Leaf/Composite types added freely); avoid leaf-aware special-casing → rely on polymorphism.

**Exam trigger words:** "tree structure", "file/folder system", "part-whole", "uniform treatment", "recursive composition".

---

## Bridge
**Problem:** An abstraction and its implementation both need to vary independently — avoid a permanent binding via inheritance (which causes class explosion when both axes grow).

**Structure**
```
Implementor (interface)              Abstraction (abstract class, HAS-A Implementor)
 ├── ConcreteImplementorA             └── RefinedAbstraction
 └── ConcreteImplementorB
```
- Abstraction delegates the "low-level" work to Implementor.
- Set once at construction (composition), unlike Decorator which can be re-wrapped repeatedly.
- Two **separate, independent** class hierarchies (vs Decorator's single hierarchy).

**SOLID:** Single Responsibility (split abstraction concerns from implementation concerns); Open/Closed (extend either hierarchy independently); Dependency Inversion (Abstraction depends on Implementor interface, not concrete class).

**Exam trigger words:** "avoid class explosion across two dimensions", "shape × color", "remote × device", "decouple abstraction from implementation", "both should vary independently".

---

## Adapter
**Problem:** Client expects interface `Target`, but the existing/reusable class (`Adaptee`) has an incompatible interface. Can't change Adaptee's code (legacy/3rd-party).

**Structure — Object Adapter (preferred in Java, uses composition)**
```
Target (interface expected by client)
 └── Adapter (implements Target, HAS-A Adaptee)
                                    Adaptee (existing incompatible class)
```
**Structure — Class Adapter (uses inheritance; Java needs Adaptee as interface, or use it only when Adaptee is itself an interface)**
```
Target (interface)        Adaptee (interface/class)
        \                  /
         Adapter extends Adaptee implements Target
```
- Java has no multiple class inheritance → **Object Adapter** (composition) is the standard/idiomatic form.
- Adapter's job: translate `request()` calls into `specificRequest()` calls — pure interface translation, no new behavior added (unlike Decorator).

**SOLID:** Single Responsibility (adapter only translates); Open/Closed (add adapters without touching Adaptee or client); Dependency Inversion (client depends on Target abstraction).

**Exam trigger words:** "incompatible interfaces", "legacy code", "third-party library", "make X work with existing client code", "wrap and translate calls".

---

## Head-to-Head Distinctions (common trick questions)

- **Decorator vs Bridge:** Both use composition + delegation. Decorator = *same* interface, stackable, runtime-recursive wrapping, purpose = add responsibility. Bridge = *two different* hierarchies fixed structurally, purpose = decouple abstraction/implementation, not about adding behavior.
- **Decorator vs Composite:** Both are recursive tree-like compositions of a common Component type. Decorator has exactly **one child** (linear chain) and **changes/adds behavior**. Composite has **0..n children** (true tree) and **aggregates/forwards behavior**, no new responsibility added.
- **Adapter vs Bridge:** Structurally similar (both wrap another object) but different **intent/timing**. Adapter is applied **after the fact** to make incompatible interfaces work together (reactive, retrofit). Bridge is designed **up front** so two hierarchies can evolve independently (proactive design).
- **Adapter vs Decorator:** Adapter **changes the interface** (translates calls). Decorator **keeps the same interface** and adds behavior.
