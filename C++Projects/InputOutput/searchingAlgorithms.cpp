#include <bits/stdc++.h>

using namespace std;
//time complexity O(n)
void search(int *set, int n, int x){
	for(int i = 0; i < n; i++){
		if(set[i] == x){
			cout << "found at index : " << i << " the element was " << set[i] << "\n";
		}
	}
}
//time complexity O(logn)
void binarySearch(int *set, int n, int x){
	int a = 0, b = n - 1;
	while(a <= b){
		int m = a+(b-a)/2;
		if(set[m]==x){
			cout << "element " << x << " found at index " << m; 
		}
		if(set[m] > x) b = m - 1;
		else a = m + 1;
	}
}
//time complexity O(logn)
void altBinarySearch(int *set, int n, int x){
	int m = 0;
	for(int b = n/2; b >= 1; b /= 2){
		while(m+b < n && set[m+b] <= x) m += b;
	}
	if(set[m] == x){	
		cout << "element " << x << " found at index " << m; 
	}
}



//lower_bound returns a pointer to the first array element whose value is atleast x
//upper_bound returns a pointer to the first array element whose value is larger than x
//equal_range returns the above pointers

void pointToVal(int *set,int n, int x){
	auto k = lower_bound(set, set+n,x)-set;
	if(k < n && set[k]==x){
		cout << "Element " << x << " is at index " << k;
	}
}

void freqOfVal(int *set, int n, int x){
	auto a = lower_bound(set, set+n, x);
	auto b = upper_bound(set, set+n, x);
	cout << "frequency of element " << x << " is " << b-a << "\n";
}

void altFreqOfVal(int *set, int n, int x){
	auto r = equal_range(set, set+n, x);
	cout << "frequency of element " << x << " is " << r.second-r.first << "\n";
}

int main(){
	int n; 
	cout << "Enter in the size of the array" << "\n";
	cin >> n;
	cout << "Enter in the values of the array for the size of " << n << "\n";
	int a[n];
	for(int i = 0; i < n; i++){
		cin >> a[i];
	}
	sort(a,a+n);
	int s;
	cout << "Enter in an inputed value to be search for" << "\n";
	cin >> s;
	//search(a,n,s);
	//binarySearch(a, n, s);
	//altBinarySearch(a,n,s);
	//pointToVal(a,n,s);
	//freqOfVal(a,n,s);
	altFreqOfVal(a,n,s);	
}
