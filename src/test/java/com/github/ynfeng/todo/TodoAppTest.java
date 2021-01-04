package com.github.ynfeng.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import com.github.ynfeng.todo.todolist.FileBasedTodoList;
import com.github.ynfeng.todo.todolist.TodoList;
import com.github.ynfeng.todo.user.CurrentUser;
import com.github.ynfeng.todo.user.User;
import com.github.ynfeng.todo.user.UserRepository;
import com.google.common.collect.Lists;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoAppTest {
    private TodoApp app;
    private ByteArrayOutputStream out;
    private ApplicationContext context;

    @BeforeEach
    public void setup() {
        out = new ByteArrayOutputStream();
        Console.out(new PrintStream(out));
        UUID testDir = UUID.randomUUID();
        UserRepository userRepository = new UserRepository() {
            private final List<User> users = Lists.newArrayList();

            @Override
            public Optional<User> findUser(String name) {
                return users.stream()
                    .filter(user -> user.name().equals(name))
                    .findAny();
            }

            @Override
            public void add(User user) {
                users.add(user);
            }
        };

        context = new ApplicationContext() {
            @Override
            public TodoList todoList(String userName) {
                return new FileBasedTodoList("/tmp/todo/" + testDir + '/');
            }

            @Override
            public UserRepository userRepository() {
                return userRepository;
            }
        };
        app = new TodoApp(context);
        Console.passwordReader(() -> "12345");
        context.userRepository().add(new User("test", "12345"));
    }

    @Test
    public void should_add_item_and_output_tips() {
        app.run(Args.of("add", "foo"));
        assertThat(out.toString(), is("1. foo\nItem foo added\n"));

        out.reset();

        app.run(Args.of("add", "bar"));
        assertThat(out.toString(), is("1. foo\n2. bar\nItem bar added\n"));
    }

    @Test
    public void should_mark_todo_item_done_and_output_tips() {
        app.run(Args.of("add", "foo"));

        out.reset();

        app.run(Args.of("done", "1"));
        assertThat(out.toString(), is("Item 1 done\n"));
    }

    @Test
    public void cant_mark_not_exists_item() {
        try {
            app.run(Args.of("done", "2"));
            fail();
        } catch (TodoApplicationException e) {
            assertThat(e.getMessage(), is("No such item"));
        }
    }

    @Test
    public void should_list_unfinished_todo_item() {
        app.run(Args.of("add", "foo"));
        app.run(Args.of("add", "bar"));
        app.run(Args.of("add", "baz"));
        app.run(Args.of("done", "1"));
        out.reset();

        app.run(Args.of("list"));
        assertThat(out.toString(), is("1. bar\n2. baz\n"));
    }

    @Test
    public void should_list_all_items() {
        app.run(Args.of("add", "foo"));
        app.run(Args.of("add", "bar"));
        app.run(Args.of("add", "baz"));
        app.run(Args.of("done", "1"));
        out.reset();

        app.run(Args.of("list", "--all"));
        assertThat(out.toString(), is("1. [Done] foo\n2. bar\n3. baz\nTotal 3 items, 1 item done\n"));
    }

    @Test
    public void should_login_with_password() {
        Console.passwordReader(() -> "12345");
        app.run(Args.of("login", "-u", "test"));

        assertThat(out.toString(), is("Password:\nLogin success!\n"));
        assertThat(CurrentUser.get().username(), is("test"));
    }

    @Test
    public void should_login_failed_when_user_not_exists() {
        Console.passwordReader(() -> "12345");
        try {
            app.run(Args.of("login", "-u", "not exists"));
            fail();
        } catch (TodoApplicationException e) {
            assertThat(e.getMessage(), is("No such user!"));
        }
    }

    @Test
    public void should_login_failed_when_password_incorrect() {
        Console.passwordReader(() -> "123");
        app.run(Args.of("login", "-u", "test"));

        assertThat(out.toString(), is("Password:\nLogin failed!\n"));
        assertThat(CurrentUser.get(), nullValue());
    }

    @Test
    public void should_logout() {
        app.run(Args.of("logout"));

        assertThat(out.toString(), is("Logout success!\n"));
        assertThat(CurrentUser.get(), nullValue());
    }

    @Test
    public void cant_login_with_wrong_arguments() {
        try {
            app.run(Args.of("login", "user"));
            fail();
        } catch (TodoApplicationException e) {
            assertThat(e.getMessage(), is("Usage: login -u <username>"));
        }
    }

    @Test
    public void should_support_multi_user() {
        String user1 = UUID.randomUUID().toString();
        String user2 = UUID.randomUUID().toString();
        context.userRepository().add(new User(user1, "12345"));
        context.userRepository().add(new User(user2, "12345"));
        app.run(Args.of("login", "-u", user1));
        app.run(Args.of("add", "user1-foo"));
        out.reset();
        app.run(Args.of("list"));
        assertThat(CurrentUser.username(), is(user1));
        assertThat(out.toString(), is("1. user1-foo\n"));

        app.run(Args.of("login", "-u", user2));
        app.run(Args.of("add", "user2-foo"));
        out.reset();
        app.run(Args.of("list"));
        assertThat(CurrentUser.username(), is(user2));
        assertThat(out.toString(), is("1. user2-foo\n"));
    }

    @Test
    public void should_add_user() {
        Console.passwordReader(() -> "123456");
        app.run(Args.of("adduser", "-u", "newUser"));
        assertThat(out.toString(), is("Password:\nuser added.\n"));
        out.reset();

        app.run(Args.of("login", "-u", "newUser"));
        assertThat(out.toString(), is("Password:\nLogin success!\n"));
    }

    @Test
    public void username_cant_duplicate() {
        Console.passwordReader(() -> "123456");
        app.run(Args.of("adduser", "-u", "test"));
        assertThat(out.toString(), is("user already exists.\n"));
    }

    @Test
    public void should_export_todo_list_to_file() throws IOException {
        String exportPath = "/tmp/todolist";
        Files.delete(Paths.get(exportPath));
        app.run(Args.of("add", "foo"));
        app.run(Args.of("add", "bar"));
        app.run(Args.of("export", ">", exportPath));

        boolean exists = Files.exists(Paths.get(exportPath), LinkOption.NOFOLLOW_LINKS);
        assertThat(exists, is(true));
        List<String> allLines = Files.readAllLines(Paths.get(exportPath));
        assertThat(allLines, is(Lists.newArrayList("{\"name\":\"foo\",\"status\":\"UnFinished\"}", "{\"name\":\"bar\",\"status\":\"UnFinished\"}")));
    }

    @AfterEach
    public void tearDown() throws IOException {
        out.close();
    }
}
