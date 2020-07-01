package Backtracking;

import java.util.*;
import java.util.stream.Collectors;

public class ReconstructItinerary {
    public static List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> targets = new HashMap<>();
        Stack<String> s = new Stack<>();
        List<String> finalRoute = new LinkedList<>();

        // targets = { 'JFK': ['A', 'B'], 'A': ['B'], etc...}
        for(List<String>ticket : tickets) {
            targets.computeIfAbsent(ticket.get(0), k->new PriorityQueue<>()).add(ticket.get(1));
//            if (!targets.containsKey(ticket.get(0))) {
//                targets.put(ticket.get(0), new PriorityQueue<>());
//            }
//            targets.get(ticket.get(0)).offer(ticket.get(1));
        }

        // Push JFK as the departure on the stack
        s.push("JFK");

        // Check if the next target has a ticket to the next place. Keep pushing to the stack till we get stuck
        while(!s.isEmpty()) {
            // If the target exists and if it's next dest is not empty
            while(targets.containsKey(s.peek()) && !targets.get(s.peek()).isEmpty())
                s.push(targets.get(s.peek()).poll());
            finalRoute.add(s.pop());
        }

        Collections.reverse(finalRoute);
        return finalRoute;
    }

    public static void main(String[] args) {
        String[][] tickets = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
        List<List<String>> list = Arrays.stream(tickets).map(Arrays::asList).collect(Collectors.toList());
        System.out.println(findItinerary(list));
    }

}

//Main Idea: Euler Path. Perform greedy DFS (lexicographical order) and backtrack to return a viable route.
//
//Thought process:
//- JFK will exist as a source ticket because we start from there
//- Add it to the stack and check if JFK has any other destinations to travel to
//- Keep exploring until we reach a destination that doesn't exist in the map
//      - Ex. {'JFK':['ATL'], 'ATL':['LAX']}, LAX does not exist in the map
//      - Ex. {'JFK':['ATL'], 'ATL':['JFK']}, JFK will not have an available destination in the queue after polling ATL
//- If we reach a dead end, pop it from the stack and add it to the front of the route
//- Once the stack is empty, that means all tickets have been used. Return the final linked list