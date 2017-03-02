import { ComponentFixture, TestBed, async } from "@angular/core/testing";
import { Todo } from "./todo";
import { TodoListComponent } from "./todo-list.component";
import { TodoListService } from "./todo-list.service";
import { Observable } from "rxjs";
import { PipeModule } from "../../pipe.module";

describe("Todo list", () => {

    let todoList: TodoListComponent;
    let fixture: ComponentFixture<TodoListComponent>;

    let todoListServiceStub: {
        getTodos: () => Observable<Todo[]>
    };

    beforeEach(() => {
        // stub TodoService for test purposes
        todoListServiceStub = {
            getTodos: () => Observable.of([
                {
                    id: "chris_id",
                    owner: "Chris",
                    status: false,
                    body: "UMM",
                    category: "love"
                },
                {
                    id: "pat_id",
                    owner: "Pat",
                    status: false,
                    body: "IBM",
                    category: "play"
                },
                {
                    id: "jamie_id",
                    owner: "Jamie",
                    status: true,
                    body: "Frogs, Inc.",
                    category: "trees"
                }
                ])
        };

        TestBed.configureTestingModule({
            imports: [PipeModule],
            declarations: [ TodoListComponent ],
            // providers:    [ TodoListService ]  // NO! Don't provide the real service!
            // Provide a test-double instead
            providers:    [ { provide: TodoListService, useValue: todoListServiceStub } ]
        })
    });

    beforeEach(async(() => {
        TestBed.compileComponents().then(() => {
            fixture = TestBed.createComponent(TodoListComponent);
            todoList = fixture.componentInstance;
            fixture.detectChanges();
        });
    }));

    it("contains all the todos", () => {
        expect(todoList.todos.length).toBe(3);
    });

    it("contains a todo by 'Chris'", () => {
        expect(todoList.todos.some((todo: Todo) => todo.owner === "Chris" )).toBe(true);
    });

    it("contain a todo by 'Jamie'", () => {
        expect(todoList.todos.some((todo: Todo) => todo.owner === "Jamie" )).toBe(true);
    });

    it("doesn't contain a todo by 'Santa'", () => {
        expect(todoList.todos.some((todo: Todo) => todo.owner === "Santa" )).toBe(false);
    });

    it("has two todos that are 37 years old", () => {
        expect(todoList.todos.filter((todo: Todo) => todo.status === false).length).toBe(2);
    });

});
