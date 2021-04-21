package ee.bcs.valiit;

import ee.bcs.valiit.tasks.Lesson1;
import ee.bcs.valiit.tasks.Lesson2;
import ee.bcs.valiit.tasks.Lesson2b;
import ee.bcs.valiit.tasks.Lesson3;
import ee.bcs.valiit.tasks.ülesanded.Employee;
import ee.bcs.valiit.tasks.ülesanded.Employee;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ControllerLessonsKuni3 {


    // lesson1 min
    //http://localhost:8080/min?a=5&b=7
    @GetMapping("min")
    public int min(@RequestParam("a") int a, @RequestParam("b") int b) {
        return Lesson1.min(a, b);
    }

    // lesson1 max
    //http://localhost:8080/max?a=5&b=6
    @GetMapping("max")
    public int max(@RequestParam("a") int a,
                   @RequestParam("b") int b) {
        return Lesson1.max(a, b);
    }

    //lesson1abs
    //http://localhost:8080/abs?a=-234
    @GetMapping("abs")
    public int abs(@RequestParam("a") int a) {
        return Lesson1.abs(a);
    }

    //lesson1 isEven
    //http://localhost:8080/isEven/5
    @GetMapping("isEven/{a}")
    public boolean isEven(@PathVariable("a") int a) {
        return Lesson1.isEven(a);
    }

    //lesson1 min3
    //http://localhost:8080/min3/3/4/6
    @GetMapping("min3/{a}/{b}/{c}")
    public int min3(@PathVariable("a") int a,
                    @PathVariable("b") int b,
                    @PathVariable("c") int c) {
        return Lesson1.min3(a, b, c);
    }

    //lesson1 max3
    //http://localhost:8080/max3/3/4/6
    @GetMapping("max3/{a}/{b}/{c}")
    public int max3(@PathVariable("a") int a,
                    @PathVariable("b") int b,
                    @PathVariable("c") int c) {
        return Lesson1.max3(a, b, c);
    }

    //lesson2 reverseArray
    //http://localhost:8080/reverseArray/1,2,3
    @GetMapping("returnArray/{1}")
    public int[] reverseArray(@PathVariable("1") int[] a) {
        return Lesson2.reverseArray(a);
    }

    //lesson2 evenNumbers
    //http://localhost:8080/evenNumbers/1,2,3,4,5,6
    @GetMapping("firstEvenNumbers/{1}")
    public int[] evenNumbers(@PathVariable("1") int a) {
        return Lesson2.evenNumbers(a);
    }

    //lesson2 min int []
    //http://localhost:8080/min/2,7,5,6,3
    @GetMapping("minElement/{1}")
    public int min2(@PathVariable("1") int[] a) {
        return Lesson2.min(a);
    }

    //lesson2 max int []
    //http://localhost:8080/max/2,7,6,4,9
    @GetMapping("maxElement/{1}")
    public int max2(@PathVariable("1") int[] a) {
        return Lesson2.max(a);
    }

    //lesson2 sum int []
    //http://localhost:8080/sum?a=x
    @GetMapping("sum/{1}")
    public int sum(@RequestParam("1") int[] x) {
        return Lesson2.sum(x);
    }

    //lesson2 fibonacci
    //http://localhost:8080/fibonacci?n
    @GetMapping("fibonacci/{n}")
    public int fibonacci(@RequestParam("n") int n) {
        return Lesson2.fibonacci(n);
    }

    //lesson2b
    //http://localhost:8080/exercise1?n
    @GetMapping("exercise1/midagi")
    public int exercise1(@RequestParam("n") int n) {
        return Lesson2b.exercise1(n);
    }


    //lesson3 factorial
    //http://localhost:8080/factorial?x
    @GetMapping("factorial/{x}")
    public int factorial(@RequestParam("x") int x) {
        return Lesson3.factorial(x);
    }


    //ülesanded Employee
    //http://localhost:8080/Employees
    @GetMapping("Employees2")
    public List<Employee> EmployeeGet() {
        Employee employee = new Employee();
        employee.setName("Veiko");
        employee.setAddress("uiio 1");
        Employee employee2 = new Employee();
        employee2.setName("Triin");
        employee2.setAddress("türi 3");
        Employee employee3 = new Employee();
        employee3.setName("Felix");
        employee3.setAddress("Rebase 16");

        list.add(employee);
        list.add(employee2);
        list.add(employee3);
        return list;
    }

    List<Employee> list = new ArrayList<>();

    //http://localhost:8080/Employees
    @GetMapping("Employees")
    public List<Employee> getEmployees() {
        return list;
    }

    @GetMapping("Employees/{index}")
    public Employee getEmployee(@PathVariable("index") int a) {
        return list.get(a);
    }

    @PostMapping("Employees")
    public void addEmployees(@RequestBody Employee employee) {
        list.add(employee);
    }

    @PutMapping("Employees/{index}")
    public void replaceEmployee(@PathVariable("index") int a, @RequestBody Employee employee) {
        list.set(a, employee);
    }

    @DeleteMapping("Employees/{index}")
    public void deleteEmployee(@PathVariable("index") int a) {
        list.remove(a);
    }


}




