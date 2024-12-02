/**
 * Validates an Italian Partita IVA (VAT number).
 * @param {string} partitaIVA - The Partita IVA to validate.
 * @returns {boolean} - Returns true if valid, otherwise false.
 */
function isValidPartitaIVA(partitaIVA) {
    // Check if the input is exactly 11 numeric characters
    const partitaIVARegex = /^\d{11}$/;
    if (!partitaIVARegex.test(partitaIVA)) {
        return false;
    }

    // Compute the checksum
    return checkPartitaIVAChecksum(partitaIVA);
}

/**
 * Computes and validates the checksum for an Italian Partita IVA.
 * @param {string} partitaIVA - The Partita IVA to validate.
 * @returns {boolean} - Returns true if the checksum is valid, otherwise false.
 */
function checkPartitaIVAChecksum(partitaIVA) {
    let sum = 0;

    for (let i = 0; i < 10; i++) {
        const digit = parseInt(partitaIVA[i], 10);

        if (i % 2 === 0) {
            // Even positions (0-based index): Add the digit directly
            sum += digit;
        } else {
            // Odd positions: Double the digit and subtract 9 if the result is >= 10
            const doubled = digit * 2;
            sum += doubled > 9 ? doubled - 9 : doubled;
        }
    }

    // Calculate the checksum digit
    const expectedChecksum = (10 - (sum % 10)) % 10;

    // Validate against the last digit
    return expectedChecksum === parseInt(partitaIVA[10], 10);
}

export {isValidPartitaIVA}