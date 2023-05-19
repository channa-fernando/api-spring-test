package com.api.spring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class BoardEntry {

    public List<List<Clan>> enterBoard(int m, List<Clan> clans) {
        Collections.sort(clans, new ClanComparator());
        List<List<Clan>> order = new ArrayList<>();
        int remainingSeats = m;
        while (!clans.isEmpty()) {
            List<Clan> group = new ArrayList<>();
            int groupSize = 0;
            for (int i = 0; i < clans.size(); i++) {
                Clan currentClan = clans.get(i);
                int clanSize = currentClan.getNumberOfPlayers();
                if (groupSize + clanSize <= remainingSeats) {
                    group.add(currentClan);
                    groupSize += clanSize;
                    clans.remove(i);
                    i--;
                }
            }
            if(!group.isEmpty()) {
            	order.add(group);
            }
            remainingSeats = m - groupSize;
        }
        return order;
    }


}

class ClanComparator implements Comparator<Clan> {
    @Override
    public int compare(Clan clan1, Clan clan2) {
        int pointsComparison = Integer.compare(clan2.getPoints(), clan1.getPoints());
        if (pointsComparison == 0) {
            return Integer.compare(clan1.getNumberOfPlayers(), clan2.getNumberOfPlayers());
        }
        return pointsComparison;
    }
}
