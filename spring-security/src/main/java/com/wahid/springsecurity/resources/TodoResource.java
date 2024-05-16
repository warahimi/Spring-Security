package com.wahid.springsecurity.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class TodoResource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final List<Todo> TODO_LIST = List.of(new Todo("admin", "Lear AWS"),
            new Todo("admin", "Lear Java"),
            new Todo("wahid", "Lear React"));

    @GetMapping("/todos")
    public List<Todo> retrieveAlltodos()
    {
        return TODO_LIST;
    }
    @GetMapping("/todos/{username}")
    public List<Todo> retrieveAlltodosForSpecificUser(@PathVariable String username)
    {
        List<Todo> result = new LinkedList<>();
        for(Todo item : TODO_LIST)
        {
            if(item.userName().equals(username))
            {
                result.add(item);
            }
        }

        return result;
    }

    @PostMapping("/todos/{username}")
    public Todo createTodoForSpecificUser(@PathVariable String username,
                                          @RequestBody Todo todo)
    {
        logger.info("Creating {}  for {}", todo, username);
        TODO_LIST.add(todo);
        return todo;
    }
}

record Todo (String userName, String Description){}
