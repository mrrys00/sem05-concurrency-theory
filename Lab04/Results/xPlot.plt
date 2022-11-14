# portion | time [s]
set terminal pngcairo enhanced font "arial,30" fontscale 1.0 size 2048, 2*768
set key left top
set title "Results\n"
set xlabel "portions"
set ylabel "time [s]"
set grid
set output 'results.png'
plot "results.dat" using 1:2 title "naive producer" lt rgb "orange" with line , "results.dat" using 1:3 title "naive consumer" lt rgb "red" with line , "results.dat" using 1:4 title "fair producer" lt rgb "blue" with line , "results.dat" using 1:5 title "fair consumer" lt rgb "green" with line 
