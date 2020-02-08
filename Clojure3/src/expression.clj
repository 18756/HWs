(defn Constant [num]
  {:pre [(number? num)]}
  {
   :evaluate       (fn [mapVars] num)
   :toStringSuffix (fn [] (format "%.1f" (double num)))
   })

(defn Variable [var]
  {:pre [(string? var)]}
  {
   :evaluate       (fn [mapVars] (get mapVars var))
   :toStringSuffix (fn [] var)
   })


(defn Operation [op strOp checkerIsNormalAmountOfArgs]
  (fn [& exps]
    {:pre [(checkerIsNormalAmountOfArgs (count exps))]}
    {
     :evaluate       (fn [mapVars] (apply op (map (fn [exp] ((exp :evaluate) mapVars)) exps)))
     :toStringSuffix (fn [] (str "(" (clojure.string/join " " (map (fn [exp] ((exp :toStringSuffix))) exps)) " " strOp ")"))
     }))


(defn leftFold [f]
  (fn [& nums] (reduce f nums)))

(defn rightFold [f]
  (fn rec [& nums]
    (if
      (== (count nums) 1) (first nums)
                          (f (first nums) (apply rec (rest nums))))))


(def Add (Operation + "+" (fn [len] (> len 0))))
(def Subtract (Operation - "-" (fn [len] (> len 0))))
(def Multiply (Operation * "*" (fn [len] (> len 0))))

(def Divide (Operation (leftFold (fn [a b] (/ (double a) (double b)))) "/" (fn [len] (> len 0))))

(def Pow (Operation (rightFold (fn [a b] (Math/pow a b))) "**" (fn [len] (== len 2))))

(def Log (Operation (rightFold (fn [a b] (/ (Math/log (Math/abs b)) (Math/log (Math/abs a))))) "//" (fn [len] (== len 2))))

(def Negate (Operation - "negate" (fn [len] (== len 1))))


(defn evaluate [exp mapVars]
  ((exp :evaluate) mapVars))

(defn toStringSuffix [exp]
  ((exp :toStringSuffix)))


(defn -result [value tail] {:value value :tail tail})

(def -valid? boolean)

(defn ignore+ [p]
  (fn [input]
    (let [res (p input)]
      (if (-valid? res) (-result 'ignore (res :tail))))))

(defn empty+ [input] (-result 'ignore input))

(defn char+ [chars]
  (fn [[c & cs]]
    (if (and c ((set chars) c)) (-result c (apply str cs)))))

(defn map+ [f p]
  (fn [input]
    (let [res (p input)]
      (if (-valid? res) (-result (f (res :value)) (res :tail))))))

(defn or- [p1 p2]
  (fn [input]
    (let [res1 (p1 input)]
      (if (-valid? res1) res1
                         (p2 input)))))


(defn or+ [& ps] (reduce or- ps))


(defn iConj [arr curValue]
  (if (= curValue 'ignore)
    arr
    (if (vector? curValue)
      (apply conj arr curValue)
      (conj arr curValue))))

(defn seq- [res p1 p2]
  (fn [input]
    (let [res1 (p1 input)]
      (if (-valid? res1)
        (let [res2 (p2 (res1 :tail))]
          (if (-valid? res2)
            (-result (apply iConj res [res1 res2]) (res2 :tail))))))))


(defn seq+ [& ps]
  (fn [input]
    (loop [res [] tail input parsers ps]
      (if (empty? parsers)
        (if (empty? res)
          (-result 'ignore tail)
          (-result res tail))
        (let [curR ((first parsers) tail)]
          (if (-valid? curR)
            (recur (iConj res (curR :value)) (curR :tail) (rest parsers))))))))




(defn word+ [word]
  (map+ (fn [res] (apply str res)) (apply seq+ (map (fn [c] (char+ (str c))) (seq word)))))


(defn p* [p]
  (fn [input]
    (loop [res [] tail input]
      (let [curR (p tail)]
        (if (and (-valid? curR) (not= (curR :tail) tail))
          (recur (iConj res (curR :value)) (curR :tail))
          (if (empty? res)
            (-result 'ignore tail)
            (-result res tail)))))))



(defn p+ [p]
  (seq+ p (p* p)))



(defn p? [p]
  (or+ p empty+))


(def open (ignore+ (char+ "(")))
(def close (ignore+ (char+ ")")))
(def space (ignore+ (char+ " ")))
(def space* (p* space))
(def space+ (p+ space))



(def digits (char+ "1234567890"))

(def number
  (map+
    (fn [res] (Constant (read-string (apply str res))))
    (seq+ (p? (char+ "-")) (p+ digits) (p? (seq+ (char+ ".") (p+ digits))))))


(def variable (map+ (fn [res] (Variable (str res))) (char+ "xyz")))


(def log (word+ "//"))
(def pow (word+ "**"))
(def negate (word+ "negate"))
(def add (word+ "+"))
(def sub (word+ "-"))
(def mul (word+ "*"))
(def div (word+ "/"))

(def operation (or+ log pow negate add sub mul div))

(def mapOperations {"+"      Add
                    "-"      Subtract
                    "*"      Multiply
                    "/"      Divide
                    "negate" Negate
                    "**"     Pow
                    "//"     Log})

(defn parseSuffix [input]
  ((map+ (fn [res]
           (if (== (count res) 1)
             (first res)
             (apply (mapOperations (last res)) (drop-last res))))
         (seq+ space*
               (or+ number
                    variable
                    (seq+ open space* (p+ (seq+ parseSuffix space+)) operation space* close)))) input))


(defn parseObjectSuffix [input]
  ((parseSuffix input) :value))


(def operand (or+ number variable))

(def priority
  {
   "+"      0
   "-"      0
   "*"      1
   "/"      1
   "negate" 3
   "**"     2
   "//"     2
   })


(defn parseInfix [input]
  ((map+ (fn [res]
           (if (== (count res) 1)
             (first res)
             (loop [priority 3 result res]
               (if (== priority -1)
                 result
                 (if (== priority 3)
                   (loop [curRes () i (- (count result) 1)]
                     ))))
             ))
         (seq+ space*
               (or+ (seq+ open space* parseInfix) operand)
               space*
               (p* (seq+ operation space*
                         (or+ (seq+ open space* parseInfix) operand)
                         space*)))) input))


(defn parseObjectInfix [input]
  ((parseInfix input) :value))

(def p parseObjectInfix)


(println (p "423324            "))
(println (p "(1 + 2 + 3)"))
(println (p "10.0"))
(println (p "x"))
(println (p "y"))
(println (p "z"))
(println (p "1 + 2"))