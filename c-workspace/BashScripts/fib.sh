# Shows fibonoacci numbers up to the first command line argument ($1)
a=0
b=1
while ((  $b < $1 )); do
	echo "$b"
	olda=$a
	a=$b
	b=$(($olda + $b))
done
