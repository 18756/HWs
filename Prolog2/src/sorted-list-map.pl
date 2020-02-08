map_get([(K, V) | _], K, V).
map_get([_ | T], K, V) :- map_get(T, K, V).

map_put([], K, V, [(K, V)]).
map_put([(K, V2) | T], K, V, [(K, V) | T]).
map_put([(K2, V2) | T], K, V, [(K, V) , (K2, V2) | T]) :- K < K2.
map_put([(K2, V2) | T], K, V, [(K2, V2) | R]) :- K > K2, map_put(T, K, V, R).

map_remove([], K, []).
map_remove([(K, V) | T], K, T).
map_remove([(K2, V2) | T], K, [(K2, V2) | R]) :- K2 < K, map_remove(T, K, R).
map_remove([(K2, V2) | T], K, [(K2, V2) | T]) :- K2 > K.

map_floorKey([(K, V) | T], K, K).
map_floorKey([(K2, V2) | T], K, R2) :- K2 < K, map_floorKey(T, K, R2).
map_floorKey([(K2, V2) | T], K, K2) :- K2 < K, \+ map_floorKey(T, K, R2).

