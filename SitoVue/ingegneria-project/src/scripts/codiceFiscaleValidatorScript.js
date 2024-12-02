/**
 * Validates an Italian fiscal code (codice fiscale).
 * @param {string} fiscalCode - The string to validate as a fiscal code.
 * @returns {boolean} - Returns true if valid, otherwise false.
 */
function isValidFiscalCode(fiscalCode) {
    // Regular expression to validate the format of an Italian fiscal code
    const fiscalCodeRegex = /^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$/;
    
    // Check the format using the regex
    if (!fiscalCodeRegex.test(fiscalCode)) {
        return false;
    }

    // Compute the checksum
    return checkFiscalCodeChecksum(fiscalCode);
}

/**
 * Computes and validates the checksum for an Italian fiscal code.
 * @param {string} fiscalCode - The fiscal code to validate.
 * @returns {boolean} - Returns true if the checksum is valid, otherwise false.
 */
function checkFiscalCodeChecksum(fiscalCode) {
    const oddValues = {
        0: 1, 1: 0, 2: 5, 3: 7, 4: 9, 5: 13, 6: 15, 7: 17, 8: 19, 9: 21,
        A: 1, B: 0, C: 5, D: 7, E: 9, F: 13, G: 15, H: 17, I: 19, J: 21,
        K: 1, L: 0, M: 5, N: 7, O: 9, P: 13, Q: 15, R: 17, S: 19, T: 21,
        U: 1, V: 0, W: 5, X: 7, Y: 9, Z: 21
    };

    const evenValues = {
        0: 0, 1: 1, 2: 2, 3: 3, 4: 4, 5: 5, 6: 6, 7: 7, 8: 8, 9: 9,
        A: 0, B: 1, C: 2, D: 3, E: 4, F: 5, G: 6, H: 7, I: 8, J: 9,
        K: 10, L: 11, M: 12, N: 13, O: 14, P: 15, Q: 16, R: 17, S: 18, T: 19,
        U: 20, V: 21, W: 22, X: 23, Y: 24, Z: 25
    };

    const characters = fiscalCode.split('');
    let checksum = 0;

    // Iterate through the fiscal code, summing values based on odd/even index
    for (let i = 0; i < characters.length - 1; i++) {
        const char = characters[i];
        if (i % 2 === 0) {
            checksum += oddValues[char] || 0;
        } else {
            checksum += evenValues[char] || 0;
        }
    }

    // Calculate the expected checksum character
    const expectedChecksumChar = String.fromCharCode((checksum % 26) + 'A'.charCodeAt(0));

    // Check if the last character matches the expected checksum
    return characters[15] === expectedChecksumChar;
}

export {isValidFiscalCode};