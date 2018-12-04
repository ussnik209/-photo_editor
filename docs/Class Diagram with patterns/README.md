### Class Diagram with patterns

![Class Diagram with patterns](
https://github.com/B1nvoker/-photo_editor/blob/master/docs/Class%20Diagram%20with%20patterns/Class%20Diagram%20with%20patterns.png)

### Command pattern

In object-oriented programming, a design pattern. A command is a behavioral pattern in which an object is used to encapsulate all the information needed to perform an action or trigger an event at a later time. This information includes the name of the method, the object that owns the method, and the values of the method parameters.

Four terms are always associated with the command pattern: commands (command), command receiver (receiver), invoking commands (invoker) and client (client). The Command object knows about the receiver and calls the receiver method. The values of the receiver parameters are stored in the command. The caller (invoker) knows how to execute a command and, possibly, does the recording and recording of executed commands. The caller (invoker) does not know anything about a particular team, he knows only about the interface. Both objects (the caller and several command objects) belong to the client object (client). The client decides which commands to execute and when. To execute a command, it passes the command object to the invoker.

Using command objects makes it easy to build common components that you need to delegate or execute method calls at any time without having to know the methods of the class or the parameters of the method. Using the calling object (invoker) allows you to enter a record of commands executed without the need to know the client about this accounting model (such records can be useful, for example, to implement undo and redo commands).

### Strategy pattern

A strategy is a behavioral design pattern designed to define a family of algorithms, encapsulate each of them, and ensure their interchangeability. This allows you to choose an algorithm by determining the appropriate class. The Strategy pattern allows you to change the selected algorithm, regardless of the client objects that use it.

### Prototype pattern

Sets the types of objects to be created using the prototype instance and creates new objects by copying this prototype. It allows you to get away from the implementation and allows you to follow the principle of "programming through interfaces." As a return type, an interface / abstract class is specified at the top of the hierarchy, and successor classes can substitute there an heir implementing this type.

Simply put, this is a pattern for creating an object through cloning another object instead of creating it through a constructor.
