/**
 * @author Sanjay Singh Rawat
 * @since 2019.08.25
 */
package com.learndatastructure.array.problem1;

/*
Arijit is a brilliant boy. He likes memory games. He likes to participate alone but this time
he has to have a partner. So he chooses you.

In this Game , your team will be shown N numbers for few minutes . You will have to memorize these numbers.

Now, the questioner will ask you Q queries, in each query He will give you a number,
and you have to tell him the total number of occurrences of that number in the array of numbers shown to your team .
If the number is not present , then you will have to say “NOT PRESENT” (without quotes).

INPUT And OUTPUT

The first line of input will contain N, an integer, which is the total number of numbers shown to your team.

The second line of input contains N space separated integers .

The third line of input contains an integer Q, denoting the total number of integers.

The Next Q lines will contain an integer denoting an integer, Bi, for which you have to print the number of occurrences
of that number (Bi) in those N numbers on a new line.

If the number Bi isn’t present then Print “NOT PRESENT” (without quotes) on a new line.

CONSTRAINTS
1≤N≤10^5
0≤Bi≤1000
1≤Q≤10^5

SAMPLE INPUT                    SAMPLE OUTPUT
6                               3
1 1 1 2 2 0                     2
6                               3
1                               1
2                               NOT PRESENT
1                               NOT PRESENT
0
3
4

 */