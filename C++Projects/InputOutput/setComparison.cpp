#include <bits/stdc++.h>

using namespace std;

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

void compareSet(int *set1, int *set2, int set1Size, int set2Size){
	mergeSort(set1, 0, set1Size);
	mergeSort(set2, 0, set2Size);
	for(int a: set1)
		
}

int main(){
	int set1[] = { 5, 2, 8, 9, 4};
	int set2[] = { 3, 2, 9, 5};
	int set1S = 5;
	int set2S = 4;
	compareSet(set1, set2);
}
