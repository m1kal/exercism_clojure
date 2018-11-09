(ns go-counting)

(defn- neighbors [grid [x y]]
  (for [i [-1 0 1] j [-1 0 1]
        :let [x (+ x i) y (+ y j)]
        :when (and (= 1 (+ (Math/abs i) (Math/abs j)))
                   (get-in grid [y x]))]
    [x y]))

(defn- find-area [grid position]
  (if (= \space (get-in grid (reverse position)))
    (loop [found #{position}]
      (let [new-pos
            (into found
              (filter
                #(= \space (get-in grid (reverse %)))
                (apply concat (map #(neighbors grid %) found))))]
        (if (= found new-pos)
          new-pos
          (recur new-pos))))
    #{}))

(defn- find-border [grid area]
  (disj
    (->> area
         (map #(neighbors grid %))
         (apply concat)
         (map #(get-in grid (reverse %)))
         set)
    \space))

(defn territory [grid position]
  (if (get-in grid (reverse position))
    (let [area (find-area grid position)
          border (find-border grid area)
          owner (cond
                  (not= 1 (count border)) nil
                  (= \B (first border)) :black
                  :else :white)]
      {:stones area :owner owner})
    (throw (AssertionError. "invalid coordinates"))))

(defn- find-territories [all-territories owner]
  (->> all-territories
       (filter #(= owner (:owner %)))
       (map :stones)
       (apply concat)
       set))

(defn territories [grid]
  (let [t (for [y (range (count grid))
                x (range (count (first grid)))]
            (territory grid [x y]))]
    {:black-territory (find-territories t :black)
     :white-territory (find-territories t :white)
     :null-territory (find-territories t nil)}))
