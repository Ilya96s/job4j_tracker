package ru.job4j.collection;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JobTest {

    @Test
    public void ascendingName() {
        List<Job> jobs = Arrays.asList(
                new Job("Artem", 0),
                new Job("Daniil", 2),
                new Job("Boris", 1)
        );
        jobs.sort(new JobAscByName());
        List<Job> expected = Arrays.asList(
                new Job("Artem", 0),
                new Job("Boris", 1),
                new Job("Daniil", 2)
        );
        assertThat(jobs, is(expected));
    }

    @Test
    public void ascendingPriority() {
        List<Job> jobs = Arrays.asList(
                new Job("Artem", 0),
                new Job("Daniil", 2),
                new Job("Boris", 1)
        );
        jobs.sort(new JobAscByPriority());
        List<Job> expected = Arrays.asList(
                new Job("Artem", 0),
                new Job("Boris", 1),
                new Job("Daniil", 2)
        );
        assertThat(jobs, is(expected));
    }

    @Test
    public void descendingName() {
        List<Job> jobs = Arrays.asList(
                new Job("Artem", 0),
                new Job("Daniil", 2),
                new Job("Boris", 1)
        );
        jobs.sort(new JobDescByName());
        List<Job> expected = Arrays.asList(
                new Job("Daniil", 2),
                new Job("Boris", 1),
                new Job("Artem", 0)
        );
        assertThat(jobs, is(expected));
    }

    @Test
    public void descendingPriority() {
        List<Job> jobs = Arrays.asList(
                new Job("Artem", 0),
                new Job("Daniil", 2),
                new Job("Boris", 1)
        );
        jobs.sort(new JobDescByPriority());
        List<Job> expected = Arrays.asList(
                new Job("Daniil", 2),
                new Job("Boris", 1),
                new Job("Artem", 0)
        );
        assertThat(jobs, is(expected));
    }

    @Test
    public void whenComparatorByNameAndPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Artem", 0),
                new Job("Artem", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByPriorityAndName() {
        Comparator<Job> cmpPriorityName = new JobAscByPriority().thenComparing(new JobAscByName());
        int rsl = cmpPriorityName.compare(
                new Job("Artem", 1),
                new Job("Boris", 1)
        );
        assertThat(rsl, lessThan(0));
    }
}