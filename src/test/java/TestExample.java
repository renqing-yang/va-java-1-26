import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * power mock ->
 */
public class TestExample {
    private static EmpRepo empRepoMock;
    private static EmpService empService;

    @BeforeAll
    public static void iniNonStatic() {
        empRepoMock = Mockito.mock(EmpRepo.class);
        empService = new EmpService(empRepoMock);
    }


    @Test
    public void firstTest() {
        assertEquals(1, 1);
    }

    @Test
    public void secondTest() {
        when(empRepoMock.getSalary(10)).thenReturn(5);
        when(empRepoMock.getSalary(1)).thenReturn(5);
//        System.out.println(empRepoMock.getSalary(1));
//        System.out.println(empRepoMock.getSalary(2));
        assertEquals(5, empService.getSalary(10));
        assertEquals(5, empService.getSalary(1));
//        EmpRepo empRepoSpy = Mockito.spy(EmpRepo.class);
//        when(empRepoSpy.getSalary(10)).thenReturn(5);
//        when(empRepoSpy.getSalary(1)).thenReturn(1000);
//        System.out.println(empRepoSpy.getSalary(1));
//        System.out.println(empRepoSpy.getSalary(2));

        verify(empRepoMock, times(1)).getSalary(10);
    }

    @Test
    public void testException() {
        assertThrows(RuntimeException.class, () -> {throw new RuntimeException();});
    }

}

@Service
class EmpService {
    private final EmpRepo empRepo;

    @Autowired
    public EmpService(EmpRepo empRepo) {
        this.empRepo = empRepo;
    }

    public int getSalary(int empId) {

        return empRepo.getSalary(empId);
    }
}

@Repository
class EmpRepo {
    public int getSalary(int empId) {
        return 5;
    }
}

/**
 *  mockmvc
 *      mockmvc().
 *          .path()
 *          .get()
 *          .expect(json result from controller..)
 *  rest assure
 */