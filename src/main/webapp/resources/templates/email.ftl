<!DOCTYPE html>
<html>
<head>
</head>
<body>
<h2 style="margin: 15px; color:rgba(0,0,0,.75)">Thank you, ${name}!
    Your booking is confirmed and paid.</h2><br>
<table  width="50%" style="margin: 15px; border-collapse: collapse">
    <thead>
    <tr style="background-color:rgba(0, 0, 0, .02);">
        <th align="right" colspan=4 style="padding: 5px; padding-right: 30px"><h2>Krakozhia luxury hotel</h2></th>
    </tr>
    </thead>
    <tbody>
    <tr style="border-bottom:1px solid rgba(0,0,0,.125)">
        <td  valign="top">
            <table width="100%">
                <tr>
                    <td width="60%" align="center"><h4>Apartment type:</h4></td>
                    <td>${apartType}</td>
                </tr>
            </table>
        </td>
        <td>
            <table width="100%" >
                <tr>
                    <td width="60%" align="center"><h4>Check-in date:</h4></td>
                    <td>${inDate}</td>
                </tr>
                <tr>
                    <td align="center"><h4>Check-out date:</h4></td>
                    <td>${outDate}</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr style="border-bottom:1px solid rgba(0,0,0,.125)">
        <td style="padding-left: 25px";><p>Apartment cost</p></td>
        <td align="right" style="padding-right: 25px";>$ ${cost}</td>
    </tr>
    <tr style="border-bottom:1px solid rgba(0,0,0,.125)">
        <td style="padding-left: 25px";><h4>Subtotal</h4></td>
        <td align="right" style="padding-right: 25px";><h4>$ ${subtotal}</h4></td>
    </tr>
    <tr style="border-bottom:1px solid black">
        <td style="padding-left: 25px";><h4>Estimated taxes</h4></td>
        <td align="right" style="padding-right: 25px";><h4>$ ${taxes}</h4></td>
    </tr>
    <tr style="border-bottom:1px solid black">
        <td style="padding-left: 25px";><h3>Total</h3></td>
        <td align="right" style="padding-right: 25px";><h3>$ ${total}</h3></td>
    </tr>
    </tbody>
</table>
</body>
</html>