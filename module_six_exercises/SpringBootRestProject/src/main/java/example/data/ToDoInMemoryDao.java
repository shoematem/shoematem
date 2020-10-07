package example.data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shoem
 */

import example.models.ToDo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ToDoInMemoryDao implements ToDoDao
{
    private static final List<ToDo> todos = new ArrayList<>();
    
    @Override
    public ToDo add(ToDo todo)
    {
        int nextId = todos.stream()
                .mapToInt(i -> i.getId())
                .max()
                .orElse(0) + 1;
        
        todo.setId(nextId);
        todos.add(todo);
        
        return todo;
    }
    
    @Override
    public List<ToDo> getAll()
    {
        return new ArrayList<>(todos);
    }
    
    @Override
    public ToDo findById(int id)
    {
        return todos.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public boolean update(ToDo todo)
    {
        int index = 0;
        
        while(index < todos.size() && todos.get(index).getId() != todo.getId())
        {
            index++;
        }
        
        if(index < todos.size())
        {
            todos.set(index, todo);
        }
        
        return index < todos.size();
    }
    
    @Override
    public boolean deleteById(int id)
    {
        return todos.removeIf(i -> i.getId() == id);
    }
}