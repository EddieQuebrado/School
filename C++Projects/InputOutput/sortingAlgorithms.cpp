#include  <bits/stdc++.h>

using namespace std;

//time complexity O(n^2)
void bubbleSort(int set[], int n){
	for(int i = 0; i < n; i++){
		for(int j = 0; j < n - 1; j++){
			if(set[j] > set[j+1]){
				swap(set[j], set[j+1]);
			}
		}
	}
}

void merge(int *set, int a, int m, int b){
	int i, j, k, c[50];
	i = a;
	k = a;
	j = m + 1;
	while(i <= m && j <= b){
		if(set[i] < set[j]){
			c[k] = set[i];
			i++;
		} else {
			c[k] = set[j];
			j++;
		}
		k++;
	}
	//copy remaining elements if any
	while(i <= m){
		c[k] = set[i];
		i++;
		k++;
	}
	//copy remaining elements if any
	while(j <= b){
		c[k] = set[j];
		j++;
		k++;
	}
	for(int i = a; i < k; i++)
		set[i] = c[i];
}


void mergeSort(int *set, int l, int r){
	if(l < r){
		int m = l+(r-l)/2;
		mergeSort(set, l, m);
		mergeSort(set, m+1, r);
		merge(set, l, m, r);
	}
}

int getMax(int *set, int n){
	int max = set[0];
	for(int i =1; i<=n; i++){
		if(set[i] > max){
			max = set[i];
		}
	}
	return max;
}	

void countSort(int *set, int n){
		int output[10];
		int count[10];
		int max = set[0];
		for(int i = 1; i < n; i++){
			if(set[i] > max){
				max = set[i];
			}
		}
		for(int i = 0; i <= max; i++){
			count[i] = 0; // initialize count array to all zero
		}
		for(int j = 0; j < n; j++){
			count[set[j]]++; //increase number count in count array
		}
		for(int i = 1; i <= max; i++){
			count[i] += count[i-1]; // find cumulative frequency
		}
		for(int i = n-1; i>=0; i--){
			output[count[set[i]]-1] = set[i];
			count[set[i]]--; //decrease count for same numbers
		}
		for(int i = 0; i < n ; i++){
			set[i] = output[i];// store output array to main array
		}
}



int main(){
	int a;
	cin >> a;
	int set[a] = { 1, 3, 8, 2, 9, 2, 5, 6};
	cout << "Given array: " << "\n";
	for(int e : set)
		cout << e << "\n";

	countSort(set, a);
	//mergeSort(set, 0, a-1);
	//bubbleSort(set, a);
	cout << "Sorted array: " << "\n";
	for(int e : set)
		cout << e << "\n";
}
