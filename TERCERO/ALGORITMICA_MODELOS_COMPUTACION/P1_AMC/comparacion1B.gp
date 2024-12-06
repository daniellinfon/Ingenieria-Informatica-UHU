set title "COMPARACION"
set xlabel "Talla"
set ylabel "Tiempo(ms)"
set xrange [500:5000]
set yrange [0:80]
plot "UnidireccionalGP" with linespoints,"BidireccionalGP" with linespoints
