(ns minesweeper
 (:require [clojure.string :refer [join split]]))

(defn- element [board x y]
  (nth (nth board y) x))

(defn- neighbor-positions [[x y] size-x size-y]
  (for [i [-1 0 1] j [-1 0 1]
        :let [x (+ x i) y (+ y j)]
        :when (and (not (= i j 0)) (< -1 x size-x)(< -1 y size-y))]
    [x y]))

(defn- count-neighbors [board position size-x size-y]
  (->>
    (neighbor-positions position size-x size-y)
    (map #(apply element board %))
    (filter #{\*})
    count))

(defn- insert-numbers [board xsize ysize]
  (for [x (range xsize) y (range ysize)]
    (if (= \space (element board x y))
        (count-neighbors board [x y] xsize ysize)
        (element board x y))))

(defn draw [board]
  (let [board (split board #"\r\n")
        xsize (count (first board))
        ysize (count board)]
    (->>
      (insert-numbers board xsize ysize)
      (replace {0 \space})
      (partition-all xsize)
      (map join)
      (join "\r\n"))))


