(ns go-counting
 (:require [clojure.set :refer [difference]]))

(defn- get-in-rev [grid]
  (fn [[x y]] (get-in grid [y x])))

(defn- neighbors [grid xy]
  (filter (get-in-rev grid) (map #(map + xy %) [[0 1] [1 0] [0 -1] [-1 0]])))

(defn- area-extension [grid area]
  (difference (set (apply concat (map #(neighbors grid %) area))) area))

(defn- find-area [grid position]
  (let [space? (fn [xy] (= \space ((get-in-rev grid) xy)))]
    (loop [found (set (filter space? [position]))]
      (let [new-pos (set (filter space? (area-extension grid found)))]
        (if (empty? new-pos)
          found
          (recur (into found new-pos)))))))

(defn- find-border [grid area]
  (set (map (get-in-rev grid) (area-extension grid area))))

(defn territory [grid position]
  (if ((get-in-rev grid) position)
    (let [area (find-area grid position)
          border (find-border grid area)
          owner (condp = border #{\W} :white #{\B} :black nil)]
      {:stones area :owner owner})
    (throw (AssertionError. "invalid coordinates"))))

(defn- find-territories [all-territories owner]
  (->> all-territories
       (filter #(= owner (:owner %)))
       (map :stones)
       (apply concat)
       set))

(defn territories [grid]
  (let [t (for [y (range (count grid)) x (range (count (first grid)))]
            (territory grid [x y]))]
    {:black-territory (find-territories t :black)
     :white-territory (find-territories t :white)
     :null-territory (find-territories t nil)}))
