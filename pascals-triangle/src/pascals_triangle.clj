(ns pascals-triangle)

(defn- add-row [previous-row]
  (map
    +
    (concat previous-row [(bigint 0)])
    (concat [(bigint 0)] previous-row)))

(defn row [n]
  (loop [row [(bigint 1)] idx 1]
    (if
      (= n idx)
      row
      (recur (add-row row) (inc idx)))))

(def triangle
  (map row (drop 1 (range))))
