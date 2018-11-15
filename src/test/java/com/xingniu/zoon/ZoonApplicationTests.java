package com.xingniu.zoon;

import com.xingniu.zoon.dao.HQCityDao;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZoonApplicationTests {

	@Autowired
	private HQCityDao hqCityDao;

	@Test
	public void contextLoads() {


	}

	@Before
	public void init() {
		random = new Random();
		stuList = new ArrayList<Student>() {
			{
				for (int i = 0; i < 100; i++) {
					add(new Student("student" + i, random.nextInt(50) + 50));
				}
			}
		};
	}

	public class Student {
		private String name;
		private Integer score;


		public Student(String name, Integer score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getScore() {
			return score;
		}

		public void setScore(Integer score) {
			this.score = score;
		}
	}

	List<Student> stuList;
	Random random ;
	//1列出班上超过85分的学生姓名，并按照分数降序输出用户名字
	@Test
	public void test1() {
		List<Integer> studentList = stuList.stream()
				.filter(x->(x.getScore()>85 || x.getScore() < 65))
				.sorted(Comparator.comparing(Student::getScore).reversed())
				.map(Student::getScore)
				.collect(Collectors.toList());
		System.out.println(studentList);
	}

	@Test
	public void testStream() {


		Random random = new Random();
		random.ints(1,20).limit(10).forEach(System.out::println);

	}


	@Test
	public void testStreamMap() {
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		// 获取对应的平方数 并去重
		List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
		System.out.println(squaresList);
	}

	@Test
	public void testStreamFilter() {

		List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		// 获取空字符串的数量
		long count = strings.stream().filter(string -> string.isEmpty()).count();
		System.out.println(count);
	}

	@Test
	public void testStreamLimit() {

		Random random = new Random();
		random.ints(1,20).limit(10).forEach(System.out::println);
	}

	@Test
	public void testStreamSort() {

		Random random = new Random();
		random.ints()
				.limit(10)
				.sorted()
				.forEach(System.out::println);
	}

	/*parallelStream 是流并行处理程序的代替方法*/
	@Test
	public void testStreamParallel() {

		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		// 获取空字符串的数量
		long count = strings.parallelStream().filter(StringUtils::isBlank).count();
		System.out.println(count);
	}

	@Test
	public void testStreamStatistics() {


		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

		IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

		System.out.println("列表中最大的数 : " + stats.getMax());
		System.out.println("列表中最小的数 : " + stats.getMin());
		System.out.println("所有数之和 : " + stats.getSum());
		System.out.println("平均数 : " + stats.getAverage());

	}


}
