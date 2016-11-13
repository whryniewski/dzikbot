(ns dzik.core)
(require '[ring.middleware.json :refer [wrap-json-response wrap-json-params]]
         '[ring.util.response :refer [response]])

(defn handler [request]
  (let [username (get-in request [:params "user_name"])
        text (get-in request [:params "text"])]
    (cond 

      (re-find #"chuj|dupa|cycki|gówno|gowno|kurw|jeba" text)
      (response {:username "Policja" 
                 :icon_url "https://www.staffordshire.police.uk/media/3509/Helmet-Icon-Clear/image/Police_Helmet_200px_blue.png" 
                 :text (format "Nie przeklinaj, %s!" username)})

      (re-find #"fuck|damn|frickin|shit" text)
      (response {:username "Police" 
                 :icon_url "https://www.staffordshire.police.uk/media/3509/Helmet-Icon-Clear/image/Police_Helmet_200px_blue.png" 
                 :text (format "Watch your language, %s!" username)})

      (re-find #"^!fortune .*" text)
      (response {:username "Wizard"
                 :text (dzik.fortune/fortune)})
      )))

(def json-handler 
  (wrap-json-params
   (wrap-json-response handler)))
