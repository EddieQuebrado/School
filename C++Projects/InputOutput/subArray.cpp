#include <bits/stdc++.h>

using namespace std;

//maximum subarray
//time complexity O(n^3)
void alg1(int set[], int n){
	int array[8];
	for(int i = 0; i < 8; i++){
		array[i] = set[i];
	}
	int best = 0;
	for(int a = 0; a <= n; a++){
		for(int b = a; b <= n; b++){
			int sum = 0;
			for(int k = a; k <= b; k++){
				sum += array[k];
			}
			best = max(best, sum);
		}
	}
	cout << best << "\n";
}

//time complexity O(n^2)
void alg2while(int set[], int n){
	int array[8];
	for(int i = 0; i < 8; i++){
		array[i] = set[i];
	}
	int best = 0;
	int l = 0;
	int r = l;
	while(l < n){
		int sum = 0;
		while(r < n){
			sum += array[r];
			cout << array[r] << "\n";
			best = max(best, sum);
			r += 1;
		}
		l += 1;
	}
	cout << best << "\n";

}

//time complexity O(n)
void alg2for(int set[], int n){
	int array[8];
	for(int i = 0; i < 8; i++){
		array[i] = set[i];
	}
	int best = 0;
	for(int a = 0; a < n; a++){
		int sum = 0;
		cout << "round: " << a << "\n";
		for(int b = a; b < n; b++){
			cout << "index value: " << array[b] << "\n";
			sum += array[b];
			best = max(best, sum);
		}
	}
	cout << best << "\n";
}

void alg3(int set[], int n){
	int best = 0, sum = 0;
	for(int k = 0; k < n; k++){
		sum = max(set[k], sum+set[k]);
		best = max(best, sum);
	}
	cout << best << "\n";
			cout << "element " << x << " found at index " << m; 
}

int main(){
	int b;
	cin >> b;
	int a[8] = { -1, 2, 4, -3, 5, 2, -5, -2};
	//alg1(a, b);
	//alg2for(a, b);
	//alg2while(a, b);
	alg3(a,b);
}
