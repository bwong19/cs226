Name: Brandon Wong  
JHED: bwong19  
Email: bwong19@jhu.edu  

## Part A: Graph Implementation
* One thing that I initially had trouble with was the implementation of the vertices() and edges() functions. I realized that I was not making a deep copy, as I simply made a copy of the list of vertices or edges. I fixed this by copying each vertex/edge and putting it into a new list.

## Part B: Street Search
* The main difficulty for me was determining the best way to implement an adaptable priority queue. I considered multiple options:
  * Creating a comparable vertex class
  * Creating a comparator to compare vertices
  * Creating a comparable information class for each vertex, then creating a map between vertex and information
* None of these options allowed me to easily convert between vertex and vertex data (distance/prev/found).
* My final solution was to modify the SparseGraph class itself, modifying the VertexNode class. I also added getters and setters to easily access vertex information.

## Part C: Touring around charm city
* Within each of my tests, the memory used differed by at most 8 kb. However, the time varied more significantly.
* While my memory usage stayed mostly the same, I can envision a scenario where memory usage can significantly vary. When creating the priority queue, all the vertex distances are set to "infinity," so Dijkstra's algorithm would break ties arbitrarily. This can be randomized within the same graph, allowing for space to vary as well.
* Similarly, time can differ because of the way Dijsktra's algorithm breaks ties. It is somewhat randomized, so certain searches may look for paths that take longer then are simplified later, and other searches may find the shortest path on the first try.
* Within locations, my path was always the same. Below are my test results:

* **JHU to 7-11**  
Network Loaded!  
Loaded 27598 roads  
Loaded 9024 endpoints  
Total Distance: 3627.5264999999995  
45662: 121.596  
40816: 137.149  
40867:N_CHARLES_ST 318.9  
42002:E_33RD_ST 60.4909  
8344:3200_BLK_N_CHARLES_ST 293.14  
11147:3200_BLK_N_CHARLES_ST 318.96  
39907:ART_MUSEUM_DR 151.623  
48094:UNIT__BLK_ART_MUSEUM_DR 664.997  
43910:ART_MUSEUM_DR 129.551  
46364:WYMAN_PARK_DR 213.639  
26692:2900_BLK_WYMAN_PARK_DR 255.018  
39554:N_HOWARD_ST 42.0276  
23133:2800_BLK_N_HOWARD_ST 124.406  
2944:2800_BLK_N_HOWARD_ST 339.908  
24854:200_BLK_W_28TH_ST 123.725  
27610:200_BLK_W_28TH_ST 119.961  
15953:200_BLK_W_28TH_ST 212.435 
 
  * Trial 1: 198 ms 20814 kb  
  * Trial 2: 246 ms 20810 kb  
  * Trial 3: 221 ms 20812 kb  
  * Trial 4: 215 ms 20810 kb  
  * Trial 5: 215 ms 20810 kb

* **JHU to Druid Lake**  
Network Loaded!  
Loaded 27598 roads  
Loaded 9024 endpoints  
Total Distance: 8818.5187  
45662: 121.596  
40816: 137.149  
40867:N_CHARLES_ST 318.9  
42002:E_33RD_ST 60.4909  
8344:3200_BLK_N_CHARLES_ST 293.14  
11147:3200_BLK_N_CHARLES_ST 318.96  
39907:ART_MUSEUM_DR 151.623  
48094:UNIT__BLK_ART_MUSEUM_DR 664.997  
43910:ART_MUSEUM_DR 129.551  
46364:WYMAN_PARK_DR 213.639  
26692:2900_BLK_WYMAN_PARK_DR 255.018  
39554:N_HOWARD_ST 42.0276  
26872:200_BLK_W_29TH_ST 136.521  
26712:200_BLK_W_29TH_ST 146.673  
15177:200_BLK_W_29TH_ST 167.427  
11871:200_BLK_W_29TH_ST 230.864  
14691:300_BLK_W_29TH_ST 196.683  
30101:300_BLK_W_29TH_ST 224.608  
5917:300_BLK_W_29TH_ST 123.841  
21125:300_BLK_W_29TH_ST 79.8048  
21194:400_BLK_W_29TH_ST 78.1632  
17656:400_BLK_W_29TH_ST 115.904  
26121:500_BLK_W_29TH_ST 600.366  
14609:2900_BLK_SISSON_ST 480.413  
23569:700_BLK_WYMAN_PARK_DR 284.616  
18109:800_BLK_WYMAN_PARK_DR 394.118  
31600:900_BLK_WYMAN_PARK_DR 281.962  
33121:900_BLK_WYMAN_PARK_DR 39.0298  
34391:1000_BLK_WYMAN_PARK 71.1794  
41471:EAST_DR 1160.95  
43386:UNNAMED_ST 190.654  
41640: 1107.65  

  * Trial 1: 405 ms 20818 kb  
  * Trial 2: 394 ms 20814 kb  
  * Trial 3: 426 ms 20810 kb  
  * Trial 4: 430 ms 20814 kb  
  * Trial 5: 440 ms 20812 kb

* **JHU to Inner Harbor**  
Network Loaded!  
Loaded 27598 roads  
Loaded 9024 endpoints  
Total Distance: 16570.4909  
45662: 121.596  
40816: 137.149  
40867:N_CHARLES_ST 318.9  
42334:N_CHARLES_ST 245.469  
42144:N_CHARLES_ST 202.146  
44656:N_CHARLES_ST 205.021  
44001:N_CHARLES_ST 195.676  
44412:N_CHARLES_ST 233.966  
41330:N_CHARLES_ST 240.143  
39032:N_CHARLES_ST 64.0439  
41710:N_CHARLES_ST 166.742  
42406:N_CHARLES_ST 231.863  
21565:2800_BLK_N_CHARLES_ST 465.662  
21531:2700_BLK_N_CHARLES_ST 467.022  
8403:2600_BLK_N_CHARLES_ST 467.965  
25136:2500_BLK_N_CHARLES_ST 165.686  
18381:2500_BLK_N_CHARLES_ST 102.259  
10673:2500_BLK_N_CHARLES_ST 75.712  
28376:2500_BLK_N_CHARLES_ST 209.449  
30800:2400_BLK_N_CHARLES_ST 204.48  
2083:2400_BLK_N_CHARLES_ST 99.9705  
18280:2400_BLK_N_CHARLES_ST 239.671  
5511:2300_BLK_N_CHARLES_ST 393.435  
26449:2200_BLK_N_CHARLES_ST 376.143  
1734:2100_BLK_N_CHARLES_ST 367.718  
11838:2000_BLK_N_CHARLES_ST 366.721  
19448:1900_BLK_N_CHARLES_ST 382.666  
20118:1800_BLK_N_CHARLES_ST 46.5814  
8607:1800_BLK_N_CHARLES_ST 244.034  
22851:1800_BLK_N_CHARLES_ST 212.368  
4876:1700_BLK_N_CHARLES_ST 386.864  
13325:1500_BLK_N_CHARLES_ST 468.441  
40898:N_CHARLES_ST 123.62  
40942:N_CHARLES_ST 107.277  
45367:I_83___S 92.1278  
43462:I_83___S 338.926  
39136:RAMP 216.46  
44623:SAINT_PAUL_ST 46.2985  
40957:SAINT_PAUL_ST 42.1892  
19656:1300_BLK_SAINT_PAUL_ST 338.907  
4356:1200_BLK_SAINT_PAUL_ST 386.857  
1691:1100_BLK_SAINT_PAUL_ST 383.173  
8766:1000_BLK_SAINT_PAUL_ST 341.452  
34028:1000_BLK_SAINT_PAUL_ST 159.482  
17769:900_BLK_SAINT_PAUL_ST 147.308  
16051:900_BLK_SAINT_PAUL_ST 347.582  
14582:800_BLK_SAINT_PAUL_ST 163.169  
2462:800_BLK_SAINT_PAUL_ST 58.6525  
24117:800_BLK_SAINT_PAUL_ST 164.639  
22312:700_BLK_SAINT_PAUL_ST 120.044  
31442:700_BLK_SAINT_PAUL_ST 201.99  
8359:700_BLK_SAINT_PAUL_ST 63.1906  
23282:600_BLK_SAINT_PAUL_ST 67.5841  
27667:600_BLK_SAINT_PAUL_ST 369.081  
35069:500_BLK_SAINT_PAUL_PL 204.024  
42343:SAINT_PAUL_PL 50.1383  
33237:500_BLK_SAINT_PAUL_PL 199.955  
36462:400_BLK_SAINT_PAUL_PL 165.177  
31819:100_BLK_ORLEANS_ST 156.75  
30373:100_BLK_ORLEANS_ST 151.147  
5947:400_BLK_N_CALVERT_ST 185.357  
9313:300_BLK_N_CALVERT_ST 147.987  
23235:300_BLK_N_CALVERT_ST 454.205  
24432:200_BLK_N_CALVERT_ST 299.369  
28959:200_BLK_N_CALVERT_ST 134.194  
33813:100_BLK_N_CALVERT_ST 296.815  
28621:200_BLK_E_FAYETTE_ST 343.863  
47459:UNIT__BLK_GUILFORD_AVE 268.592  
47548:UNIT__BLK_SOUTH_ST 271.099  
47419:UNIT__BLK_SOUTH_ST 158.76  
47386:UNIT__BLK_SOUTH_ST 71.8061  
48137:UNIT__BLK_SOUTH_ST 163.038  
20226:100_BLK_SOUTH_ST 462.642  

  * Trial 1: 216 ms 20812 kb  
  * Trial 2: 230 ms 20814 kb  
  * Trial 3: 220 ms 20814 kb  
  * Trial 4: 211 ms 20816 kb  
  * Trial 5: 238 ms 20812 kb

