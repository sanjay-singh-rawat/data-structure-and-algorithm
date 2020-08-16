package com.learndatastructure.interviewcake.arrayandstringmanipulation.mergeingmeetingtimes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sanjay Singh Rawat
 * @since 2020.08.14
 */
public class HiCal {

    public static void main(String[] args) {
        meetingsOverlapTest();
        meetingsTouchTest();
        meetingContainsOtherMeetingTest();
        meetingsStaySeparateTest();
        multipleMergedMeetingsTest();
        meetingsNotSortedTest();
        oneLongMeetingContainsSmallerMeetingsTest();
        sampleInputTest();
    }

    public static List<Meeting> mergeRanges(List<Meeting> meetings) {

        List<Meeting> sortedMeetings = new ArrayList<>();

        for (Meeting meeting : meetings) {
            Meeting meetingCopy = new Meeting(meeting.getStartTime(), meeting.getEndTime());
            sortedMeetings.add(meetingCopy);
        }

        sortedMeetings.sort((o1, o2) -> o1.getStartTime() - o2.getEndTime());

        List<Meeting> mergedMeetings = new ArrayList<>();
        mergedMeetings.add(sortedMeetings.get(0));

        for (Meeting currentMeeting : sortedMeetings) {
            Meeting lastMergedMeeting = mergedMeetings.get(mergedMeetings.size() - 1);
            if (currentMeeting.getStartTime() <= lastMergedMeeting.getEndTime()) {
                lastMergedMeeting.setEndTime(Math.max(currentMeeting.getEndTime(), lastMergedMeeting.getEndTime()));
            } else {
                mergedMeetings.add(currentMeeting);
            }
        }

        return mergedMeetings;

    }

    public static void meetingsOverlapTest() {
        final List<Meeting> meetings = Arrays.asList(new Meeting(1, 3), new Meeting(2, 4));
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(new Meeting(1, 4));
        assert expected.equals(actual);
    }

    public static void meetingsTouchTest() {
        final List<Meeting> meetings = Arrays.asList(new Meeting(5, 6), new Meeting(6, 8));
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(new Meeting(5, 8));
        assert expected.equals(actual);
    }

    public static void meetingContainsOtherMeetingTest() {
        final List<Meeting> meetings = Arrays.asList(new Meeting(1, 8), new Meeting(2, 5));
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(new Meeting(1, 8));
        assert expected.equals(actual);
    }

    public static void meetingsStaySeparateTest() {
        final List<Meeting> meetings = Arrays.asList(new Meeting(1, 3), new Meeting(4, 8));
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(
                new Meeting(1, 3), new Meeting(4, 8)
        );
        assert expected.equals(actual);
    }

    public static void multipleMergedMeetingsTest() {
        final List<Meeting> meetings = Arrays.asList(
                new Meeting(1, 4), new Meeting(2, 5), new Meeting (5, 8));
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(new Meeting(1, 8));
        assert expected.equals(actual);
    }

    public static void meetingsNotSortedTest() {
        final List<Meeting> meetings = Arrays.asList(
                new Meeting(5, 8), new Meeting(1, 4), new Meeting(6, 8));
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(
                new Meeting(1, 4), new Meeting(5, 8)
        );
        assert expected.equals(actual);
    }

    public static void oneLongMeetingContainsSmallerMeetingsTest() {
        final List<Meeting> meetings = Arrays.asList(
                new Meeting(1, 10), new Meeting(2, 5), new Meeting(6, 8),
                new Meeting(9, 10), new Meeting(10, 12)
        );
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(
                new Meeting(1, 12)
        );
        assert expected.equals(actual);
    }

    public static void sampleInputTest() {
        final List<Meeting> meetings = Arrays.asList(
                new Meeting(0, 1), new Meeting(3, 5), new Meeting(4, 8),
                new Meeting(10, 12), new Meeting(9, 10)
        );
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(
                new Meeting(0, 1), new Meeting(3, 8), new Meeting(9, 12)
        );
        assert expected.equals(actual);
    }
}
