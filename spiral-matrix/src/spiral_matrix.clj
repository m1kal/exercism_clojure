(ns spiral-matrix)

(defn- build-empty-matrix [n]
  (vec (repeat n (vec (repeat n 0))))
  )

(defn- next_position [n position direction bounce]
  (let [next_pos (map (fn [x dx] (+ x dx)) position direction)
        next_dir (cond
          (and (= direction [1 0]) (= (first next_pos) (- (dec n) bounce))) [0 1]
          (and (= direction [0 1]) (= (last next_pos) (- (dec n) bounce))) [-1 0]
          (and (= direction [-1 0]) (= (first next_pos) bounce)) [0 -1]
          (and (= direction [0 -1]) (= (last next_pos) bounce)) [1 0]
          :else direction)
               ]
  [next_pos next_dir])
  )

(defn spiral [n]
  (let [max_val (* n n)]
  (loop [matrix (build-empty-matrix n) current 1 pos_dir [[0 0] [1 0]] bounce 1]
    (if (> current max_val)
     matrix
     (let [next_pos_dir (next_position n (first pos_dir) (last pos_dir) (int (Math/floor (/ bounce 4))))]
     (recur (update-in matrix (reverse (first pos_dir)) + current) (inc current) next_pos_dir
      (if (= (last next_pos_dir) (last pos_dir)) bounce (inc bounce))
       )))))
  )
