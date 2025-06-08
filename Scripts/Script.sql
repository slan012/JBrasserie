SELECT idOrder, b.*, quantity 
FROM orderline ol
join beer b on b.idBeer = ol.idBeer
WHERE idOrder = 3