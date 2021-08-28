package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.Post;
import me.seo.studyjpa.domain.TestSave;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TestSaveRepositoryTest {
    @Autowired
    TestSaveRepository testSaveRepository;


    @Test
    public void save(){
        // RETURN 받은 인스턴스를 사용해라!
        TestSave testSave = new TestSave();
        testSave.setTitle("jpa");
        testSaveRepository.save(testSave); // insert // persist // id가 없기 때문에 Entity manager persist 호출

        TestSave testSave2 = new TestSave();
        testSave2.setId(testSave.getId());
        testSave2.setTitle("hibernate");
        testSaveRepository.save(testSave2); // update // merge

        List<TestSave> all = testSaveRepository.findAll();

    }
    @Test
    public void findTitle(){
        TestSave testSave = new TestSave();
        testSave.setTitle("start test jpa");
        testSaveRepository.save(testSave);

        List<TestSave> start = testSaveRepository.findByTitleStartingWith("start");
        assertEquals(1,start.size());


    }
    @Test
    public void findByTitle(){
        TestSave testSave = new TestSave();
        testSave.setTitle("test");
        testSaveRepository.save(testSave);

        // Sort.by( title ) 가능 title 프로퍼티
        // 프로퍼티 or alias 만 가능 하므로 함수 사용시 우회해야함 -> jpaUnsafe
        //List<TestSave> test = testSaveRepository.findByTitle("test", Sort.by("title"));
        List<TestSave> test = testSaveRepository.findByTitle("test", JpaSort.unsafe("LENGTH(title)"));
        assertEquals(1,test.size());
    }
    // update 쿼리는 hibernate가 data sink할 때 해주지만 성능 상의 이유나 update 사용하고 싶은 경우 사용하는 방법
    // 이 방법 보다 updateTitleProv 가 더 좋음
    @Test
    public void updateTitle(){
        TestSave testSave = new TestSave();
        testSave.setTitle("test");
        TestSave save = testSaveRepository.save(testSave);

        int i = testSaveRepository.updateTitle("hibernate", save.getId());
        assertEquals(1,i);
    }
    @Test
    public void updateTitleProv(){
        TestSave testSave = new TestSave();
        testSave.setTitle("test");
        TestSave save = testSaveRepository.save(testSave);
        save.setTitle("hibernate");

        List<TestSave> all = testSaveRepository.findAll();
        assertEquals("hibernate",all.get(0).getTitle());
    }

}