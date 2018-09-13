(ns binary-search-tree)

(defn value [tree]
  (:value tree)
)

(defn singleton [input]
  {:value input}
)

(defn insert [input tree]
  (assoc-in
    tree
    (loop [position [] branch tree]
      (if
        (nil? branch)
        position
        (let [dir (if (< (value branch) input) :right :left)]
          (recur (conj position dir) (dir branch)))))
    (singleton input)))


(defn left [tree]
  (:left tree)
)

(defn right [tree]
  (:right tree)
)

(defn to-list [tree]
  (loop [output [] tree {:head tree} path [:head]]
    (let [branch (get-in tree path)
          output (if (left branch) output (conj output (value branch)))]
      (cond
        (left branch) (recur output tree (conj path :left))
        (right branch) (recur output (assoc-in tree path (right branch)) path)
        (> (count path) 1) (recur output (assoc-in tree path nil) [:head])
        :else output))))

(defn from-list [input]
  (reduce #(insert %2 %1) (singleton (first input)) (rest input))
)
