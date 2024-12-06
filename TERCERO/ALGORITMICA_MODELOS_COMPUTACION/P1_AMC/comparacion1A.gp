set title "COMPARACION"
set xlabel "Talla"
set ylabel "Tiempo(ms)"
set xrange [1000:50000]
set yrange [0:30]
plot "ExhaustivoGP" with linespoints,"ExhaustivoPodaGP" with linespoints,"DyVGP" with linespoints,"DyVMejoradoGP" with linespoints
