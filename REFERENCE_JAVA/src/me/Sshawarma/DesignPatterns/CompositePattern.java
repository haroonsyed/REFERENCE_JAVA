package me.Sshawarma.DesignPatterns;

//Treat each sub tree of a hierarchical structure as if it were an individual component and
//be able to uniformly call operations on those sub components. Similar to what component based frameworks
//like react use.

//In this example we call common operations on components of a todoList

import java.util.ArrayList;
import java.util.List;

interface TodoComponent {
    public String getText();
}

//Leaf node
class Todo implements TodoComponent {

    private String todo;

    public Todo(String todo) {
        this.todo = todo;
    }

    @Override
    public String getText() {
        return todo;
    }

}

//Composite (has sub todos)
class Project implements TodoComponent {

    private String groupName;
    List<TodoComponent> todos;

    public Project(String groupName, List<TodoComponent> todos) {
        this.groupName = groupName;
        this.todos = todos;
    }



    @Override
    public String getText() {
        String todoText = groupName + ": \n";
        for(TodoComponent todo : todos) {
            todoText += todo.getText() + ", ";
        }
        //Did this just to make the horrible formatting a bit clearer
        todoText += "\n END OF " + groupName;
        return todoText;
    }

}




public class CompositePattern {

    public CompositePattern() {

        //Let's create a simple todolist and print it from master node
        List<TodoComponent> todos = new ArrayList<TodoComponent>();
        //Let's add two todos to the todos list
        todos.add(new Todo("Wash Dishes"));
        todos.add(new Todo("Take out trash"));

        //holds our whole structure
        TodoComponent masterNode = new Project("Master Node", todos);

        //Now we add one project to the todos
        List<TodoComponent> project = new ArrayList<TodoComponent>();
        todos.add(new Project("Learn Programming", project));
        //Add two tasks to the Learn Programming project
        project.add(new Todo("Learn MERN stack"));
        project.add(new Todo("Learn Design Patterns"));

        //Now we can call the same function on each component, since the composite shares leaf function getText
        System.out.println(masterNode.getText());

    }

}
