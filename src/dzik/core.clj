(ns dzik.core)
(require '[ring.middleware.json :refer [wrap-json-response wrap-json-params]]
         '[ring.util.response :refer [response]]
         '[dzik.fortune]
         '[dzik.giphy]
         '[dzik.images]
         '[clojure.string :as str]
         '[ring.middleware.params :as params])

(defn giphyhandler [orig-params]
  (let [params (orig-params :params)] 
    (println params)
    (cond 
      (= (params "command") "/giphy")
      (response {:response_type "in_channel"
                 :username "Giphosaurus" 
                 :icon_url "http://findicons.com/files/icons/7/dinosaurs_toys/128/tyrannosaurus_rex.png" 
                 :text 
                 (if (= (params "channel_name") "general-en")
                   (format "%s cannot into %s (all work and no play makes Jack a dull boy...)" (params "user_name") (params "text"))
                   (format "%s wants some %s:\n ![giphy](%s)"
                           (params "user_name")
                           (if (< 0 (count (str/trim (params "text")))) (params "text") "random giphyyy")
                           (if (< 0 (count (str/trim (params "text")))) (dzik.giphy/search (str/trim (params "text"))) (dzik.giphy/random))))
                 })
      (= (params "command") "/cowsay")
      (response {:response_type "in_channel"
                 :username "Giphosaurus" 
                 :icon_url "http://findicons.com/files/icons/7/dinosaurs_toys/128/tyrannosaurus_rex.png" 
                 :text 
                 (format "%s wants cow to say:\n ![cowsay](%s)"
                         (params "user_name")
                         (if (< 0 (count (str/trim (params "text"))))
                           (dzik.images/add-text-get-url (str/trim (params "text")))
                           (dzik.images/add-text-get-url (str/trim (params "text")))))
                 })
      
      (= (params "trigger_word") "butter")
      (response {:response_type "in_channel"
                 :username "Robot" 
                 :icon_url "https://vignette4.wikia.nocookie.net/rickandmorty/images/7/77/Butter_Robot.png/revision/latest?cb=20160910011723" 
                 :text ";_;"
                 
                 }))))
             

(def giphy-handler (params/wrap-params (wrap-json-response giphyhandler)))

(def json-handler 
  (wrap-json-params
   (wrap-json-response handler)))
